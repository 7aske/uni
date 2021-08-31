import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AdminGuard } from "../guards/admin.guard";
import { PostListComponent } from "./post-list/post-list.component";
import { EditPostComponent } from "./edit-post/edit-post.component";
import { EditCategoryComponent } from "./edit-category/edit-category.component";
import { EditTagComponent } from "./edit-tag/edit-tag.component";

const routes: Routes = [
	{
		path: "",
		canActivate: [AdminGuard],
		children: [
			{
				path: "",
				component: PostListComponent,
			},
			{
				path: "edit",
				component: EditPostComponent,
			},
			{
				path: "edit/:id",
				component: EditPostComponent,
			},
			{
				path: "categories",
				component: EditCategoryComponent,
			},
			{
				path: "tags",
				component: EditTagComponent,
			},
			{
				path: "**",
				redirectTo: "",
				pathMatch: "full",
			},
		],
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AdminRoutingModule {
}
