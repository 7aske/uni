import { Component, EventEmitter, OnInit, Input, Output } from "@angular/core";
import { Post } from "../../../../../common/src/@types/entity/Post";
import { environment } from "../../../environments/environment";
import { ConfirmDialogService } from "../../../../../common/src/services/confirm-dialog.service";

@Component({
	selector: "admin-post-list-item",
	templateUrl: "./post-list-item.component.html",
	styleUrls: ["./post-list-item.component.scss"],
})
export class PostListItemComponent implements OnInit {
	public blogUrl = environment.blogBaseUrl;
	@Input()
	public post: Post;
	@Output()
	public onDelete = new EventEmitter<number>();

	constructor(private confirmDialogService: ConfirmDialogService) {
	}

	ngOnInit(): void {
	}

	public delete() {
		const message = "Are you sure to delete?";
		this.confirmDialogService.confirm(message, () => {
			this.onDelete.emit(this.post.id);
		});
	}
}
