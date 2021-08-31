import { Observable } from "rxjs";
import { UrlTree } from "@angular/router";

declare type GuardReturn =
	Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree;
