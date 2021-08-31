import { Injectable } from "@angular/core";
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { GuardReturn } from "../../../common/src/@types/GuardReturn";
import { JwtService } from "../../../common/src/services/jwt.service";
import { hasRole } from "../../../common/src/utils/role.utils";
import { environment } from "../environments/environment";

@Injectable({
	providedIn: "root",
})
export class AdminGuard implements CanActivate {
	constructor(private jwtService: JwtService) {
	}

	canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): GuardReturn {
		try {
			const token = this.jwtService.getToken();
			const isAdmin = hasRole(token.roles, "admin");
			if (isAdmin) return true;
		} catch (e) {
			// ignored
		}
		window.location.href = environment.loginBaseUrl;
		return false;
	}
}
