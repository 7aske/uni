import { Component } from "@angular/core";
import { trigger, state, style, transition, animate } from "@angular/animations";

@Component({
	selector: "blog-post-list-skeleton",
	template: `
		<div @fadeOut>
			<ngx-skeleton-loader [theme]="{
				'border-radius': '5px',
				'height': '50px',
				'width': '300px',
				'background-color': '#4ecca3'
			}" animation="pulse"></ngx-skeleton-loader>
			<ngx-skeleton-loader [theme]="{
				'border-radius': '5px',
				'height': '150px',
				'background-color': '#7d7d7d'
			}" animation="pulse"></ngx-skeleton-loader>
			<ngx-skeleton-loader [count]="randomCount()" [theme]="{
				'border-radius': '10px',
				'height': '25px',
				'width': '50px',
				'margin-right': '5px',
				'background-color': '#4ecca3'
			}" animation="pulse"></ngx-skeleton-loader>
		</div>
	`,
	animations: [
		trigger("fadeOut", [
			state("*", style({
				opacity: 1,
			})),
			transition("* <=> void", animate(500)),
		]),
	],
})
export class PostListSkeletonComponent {
	public randomCount() {
		return Math.round(Math.random() * 5);
	}
}
