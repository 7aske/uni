import { Component, OnInit } from "@angular/core";
import { JwtService } from "../../services/jwt.service";
import { environment } from "../../environments/environment";

@Component({
	selector: "common-navbar",
	templateUrl: "./navbar.component.html",
	styleUrls: ["./navbar.component.scss"],
})
export class NavbarComponent implements OnInit {
	public loggedIn = false;
	public loginUrl = environment.loginBaseUrl;
	public adminUrl = environment.adminBaseUrl;
	public blogUrl = environment.blogBaseUrl;

	constructor(private jwtService: JwtService) {
		console.log(environment);
	}

	ngOnInit(): void {
		this.loggedIn = this.jwtService.isLoggedIn();
	}

	public logout(){
		this.jwtService.unsetToken();
		window.location.href = this.loginUrl;
	}
}
