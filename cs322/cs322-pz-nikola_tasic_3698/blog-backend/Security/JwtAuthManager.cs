using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using blog_backend.Data;
using Microsoft.IdentityModel.Tokens;

namespace blog_backend.Security;

public class JwtAuthManager {
	private readonly JwtTokenConfig _jwtTokenConfig;
	private readonly byte[] _secret;

	public JwtAuthManager(JwtTokenConfig jwtTokenConfig) {
		_jwtTokenConfig = jwtTokenConfig;
		_secret = Encoding.ASCII.GetBytes(jwtTokenConfig.Secret);
	}

	public JwtAuthResult GenerateTokens(User user, List<String> roles) {
		var signingCredentials = new SigningCredentials(
			new SymmetricSecurityKey(_secret),
			SecurityAlgorithms.HmacSha256Signature);
		var payload = new JwtPayload {
			{"user", user.Username},
			{"sub", user.Id},
			{"roles", roles},
			{"aud", _jwtTokenConfig.Audience},
			{"iss", _jwtTokenConfig.Issuer},
			{"iat", DateTimeOffset.UtcNow.ToUnixTimeSeconds()},
			{"exp", DateTimeOffset.UtcNow.AddHours(2).ToUnixTimeSeconds()}
		};
		var token =
			new JwtSecurityToken(new JwtHeader(signingCredentials), payload);
		var accessToken = new JwtSecurityTokenHandler().WriteToken(token);

		return new JwtAuthResult {
			Token = accessToken,
		};
	}

	public (ClaimsPrincipal, JwtSecurityToken) DecodeJwtToken(string token) {
		if (string.IsNullOrWhiteSpace(token)) {
			throw new SecurityTokenException("Invalid token");
		}

		var principal = new JwtSecurityTokenHandler()
			.ValidateToken(token,
				new TokenValidationParameters {
					ValidateIssuer = true,
					ValidIssuer = _jwtTokenConfig.Issuer,
					ValidateIssuerSigningKey = true,
					IssuerSigningKey = new SymmetricSecurityKey(_secret),
					ValidAudience = _jwtTokenConfig.Audience,
					ValidateAudience = true,
					ValidateLifetime = true,
					ClockSkew = TimeSpan.FromMinutes(1)
				},
				out var validatedToken);
		return (principal, validatedToken as JwtSecurityToken);
	}
}

public class JwtAuthResult {
	public string Token { get; set; }
}