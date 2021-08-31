import { Component, OnInit, Input } from "@angular/core";
import { Post } from "../../../../../common/src/@types/entity/Post";
import { trigger, state, style, transition, animate } from "@angular/animations";

@Component({
	selector: "blog-post-list-item",
	templateUrl: "./post-list-item.component.html",
	styleUrls: ["./post-list-item.component.scss"],
	animations: [
		trigger('fadeInOut', [
			state('void', style({
				opacity: 0
			})),
			transition('void <=> *', animate(500)),
		]),
	]
})
export class PostListItemComponent implements OnInit {
	@Input()
	public post: Post;

	constructor() {
	}

	ngOnInit(): void {
	}

}
