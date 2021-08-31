import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./login.component";

const routes: Routes = [
	{
		path: "",
		component: LoginComponent,
	},
	{
		path: "admin",
		redirectTo: "/admin"
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class LoginRoutingModule {
}
