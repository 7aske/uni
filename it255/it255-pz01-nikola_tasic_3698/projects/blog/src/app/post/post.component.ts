import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { Post } from "../../../../common/src/@types/entity/Post";
import { PostService } from "../../services/post.service";
import { ActivatedRoute } from "@angular/router";

@Component({
	selector: "blog-post",
	templateUrl: "./post.component.html",
	styleUrls: ["./post.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class PostComponent implements OnInit {
	public post: Post;

	constructor(private postService: PostService, private route: ActivatedRoute) {
	}

	ngOnInit(): void {
		const slug = this.route.snapshot.paramMap.get("slug")!;
		this.fetchPost(slug);
	}

	private async fetchPost(slug: string) {
		this.post = await this.postService.getBySlug(slug);
	}

}
