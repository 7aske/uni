import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AdminComponent } from "./admin.component";
import { AdminRoutingModule } from "./admin-routing.module";
import { CommonModule } from "projects/common/src/common-module";
import { NgxPaginationModule } from "ngx-pagination";
import { PostListComponent } from "./post-list/post-list.component";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { LoggingInterceptor } from "../../../common/src/services/interceptors/logging.interceptor";
import { PostListItemComponent } from "./post-list/post-list-item/post-list-item.component";
import { SidenavComponent } from "./sidenav/sidenav.component";
import { MomentModule } from "angular2-moment";
import { ToastrModule } from "ngx-toastr";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { EditPostComponent } from "./edit-post/edit-post.component";
import { TooltipModule } from "ng2-tooltip-directive";
import { ReactiveFormsModule } from "@angular/forms";
import { SimplemdeModule, SIMPLEMDE_CONFIG } from "ng2-simplemde";
import * as marked from "marked";
import { ScrollToModule } from "@nicky-lenaers/ngx-scroll-to";
import { AuthInterceptor } from "../../../common/src/services/interceptors/auth.interceptor";
import { EditCategoryComponent } from './edit-category/edit-category.component';
import { EditTagComponent } from './edit-tag/edit-tag.component';


@NgModule({
	declarations: [
		AdminComponent,
		PostListComponent,
		PostListItemComponent,
		SidenavComponent,
		EditPostComponent,
		EditCategoryComponent,
		EditTagComponent,
	],
	imports: [
		AdminRoutingModule,
		BrowserModule,
		BrowserAnimationsModule,
		CommonModule,
		NgxPaginationModule,
		MomentModule,
		TooltipModule,
		ScrollToModule.forRoot(),
		ToastrModule.forRoot({
			timeOut: 5000,
			maxOpened: 5,
			preventDuplicates: true,
			enableHtml: true,
			positionClass: "toast-bottom-center",
		}),
		SimplemdeModule.forRoot({
			provide: SIMPLEMDE_CONFIG,
			useValue: {
				sideBySideFullscreen: true,
				previewRender: (plainText: string) => {
					return marked(plainText, {gfm: true}); // Returns HTML from a custom parser
				},
				showIcons: ["strikethrough", "code", "table", "redo", "heading", "undo", "horizontal-rule"],
				renderingConfig: {
					codeSyntaxHighlighting: true,
					markedOptions: {gfm: true},
				},
				spellChecker: false,
				status: ["autosave", "cursor"],

			},
		}),
		ReactiveFormsModule,
	],
	providers: [
		{provide: HTTP_INTERCEPTORS, useClass: LoggingInterceptor, multi: true},
		{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
	],
	bootstrap: [AdminComponent],
})
export class AdminModule {
}
