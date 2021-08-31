import { Component, OnInit } from "@angular/core";
import { UserInfoService } from "../../../../common/src/services/user-info.service";
import { User } from "../../../../common/src/@types/entity/User";
import * as M from "materialize-css";
import Console from "projects/common/src/utils/console";

@Component({
	selector: "admin-sidenav",
	templateUrl: "./sidenav.component.html",
	styleUrls: ["./sidenav.component.scss"],
})
export class SidenavComponent implements OnInit {
	public user: User;

	constructor(private userInfoService: UserInfoService) {
	}

	ngOnInit(): void {
		M.Sidenav.init(document.querySelector("#admin-sidenav")!, {draggable: true, edge: "right"});
		Console.log("test")
		this.userInfoService.getLoggedInUser()
			.then(user => this.user = user);
	}
}
