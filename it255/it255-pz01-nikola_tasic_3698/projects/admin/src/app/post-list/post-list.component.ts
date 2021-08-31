import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { PostService } from "../../services/post.service";
import { Post } from "../../../../common/src/@types/entity/Post";
import { ToastrService } from "ngx-toastr";

@Component({
	selector: "admin-post-list",
	templateUrl: "./post-list.component.html",
	styleUrls: ["./post-list.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class PostListComponent implements OnInit {
	public posts: Post[] = [];
	public page: number = 0;

	constructor(private postService: PostService,
	            private toastService: ToastrService) {
	}

	ngOnInit(): void {
		this.postService.getAll()
			.then(_posts => this.posts = _posts);
	}

	public onDelete(id: number) {
		this.postService.delete(id)
			.then(() => {
				this.posts = this.posts.filter(p => p.id !== id);
				this.toastService.info("Post deleted", "Delete");
			});
	}
}
