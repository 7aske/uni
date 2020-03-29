package http;

public class HttpStatus {
	public static String getStatusText(StatusCodes s) {
		switch (s) {
			case Continue:
				return "Continue";
			case SwitchingProtocols:
				return "Switching Protocols";
			case OK:
				return "OK";
			case Created:
				return "Created";
			case Accepted:
				return "Accepted";
			case NonAuthoritativeInformation:
				return "Non-Authoritative Information";
			case NoContent:
				return "No Content";
			case ResetContent:
				return "Reset Content";
			case PartialContent:
				return "Partial Content";
			case MultipleChoices:
				return "Multiple Choices";
			case MovedPermanently:
				return "Moved Permanently";
			case Found:
				return "Found";
			case SeeOther:
				return "See Other";
			case NotModified:
				return "Not Modified";
			case UseProxy:
				return "Use Proxy";
			case TemporaryRedirect:
				return "Temporary Redirect";
			case BadRequest:
				return "Bad Request";
			case Unauthorized:
				return "Unauthorized";
			case PaymentRequired:
				return "Payment Required";
			case Forbidden:
				return "Forbidden";
			case NotFound:
				return "Not Found";
			case MethodNotAllowed:
				return "Method Not Allowed";
			case NotAcceptable:
				return "Not Acceptable";
			case ProxyAuthenticationRequired:
				return "Proxy Authentication Required";
			case RequestTimeout:
				return "Request Time-out";
			case Conflict:
				return "Conflict";
			case Gone:
				return "Gone";
			case LengthRequired:
				return "Length Required";
			case PreconditionFailed:
				return "Precondition Failed";
			case RequestEntityTooLarge:
				return "Request Entity Too Large";
			case RequestURITooLarge:
				return "Request-URI Too Large";
			case UnsupportedMediaType:
				return "Unsupported Media Type";
			case RequestedRangeNotSatisfiable:
				return "Requested range not satisfiable";
			case ExpectationFailed:
				return "Expectation Failed";
			case InternalServerError:
				return "Internal Server Error";
			case NotImplemented:
				return "Not Implemented";
			case BadGateway:
				return "Bad Gateway";
			case ServiceUnavailable:
				return "Service Unavailable";
			case GatewayTimeout:
				return "Gateway Time-out";
			case HTTPVersionNotSupported:
				return "http Version not supported";
			default:
				return "";
		}
	}
	public static int getStatusCode(StatusCodes s) {
		switch (s) {
			case Continue:
				return 100;
			case SwitchingProtocols:
				return 101;
			case OK:
				return 200;
			case Created:
				return 201;
			case Accepted:
				return 202;
			case NonAuthoritativeInformation:
				return 203;
			case NoContent:
				return 204;
			case ResetContent:
				return 205;
			case PartialContent:
				return 206;
			case MultipleChoices:
				return 300;
			case MovedPermanently:
				return 301;
			case Found:
				return 302;
			case SeeOther:
				return 303;
			case NotModified:
				return 304;
			case UseProxy:
				return 305;
			case TemporaryRedirect:
				return 307;
			case BadRequest:
				return 400;
			case Unauthorized:
				return 401;
			case PaymentRequired:
				return 402;
			case Forbidden:
				return 403;
			case NotFound:
				return 404;
			case MethodNotAllowed:
				return 405;
			case NotAcceptable:
				return 406;
			case ProxyAuthenticationRequired:
				return 407;
			case RequestTimeout:
				return 408;
			case Conflict:
				return 409;
			case Gone:
				return 410;
			case LengthRequired:
				return 411;
			case PreconditionFailed:
				return 412;
			case RequestEntityTooLarge:
				return 413;
			case RequestURITooLarge:
				return 414;
			case UnsupportedMediaType:
				return 415;
			case RequestedRangeNotSatisfiable:
				return 416;
			case ExpectationFailed:
				return 417;
			case InternalServerError:
				return 500;
			case NotImplemented:
				return 501;
			case BadGateway:
				return 502;
			case ServiceUnavailable:
				return 503;
			case GatewayTimeout:
				return 504;
			case HTTPVersionNotSupported:
				return 505;
			default:
				return 0;
		}
	}
}

