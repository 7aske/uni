import { Component, OnInit, Input, ViewEncapsulation, ChangeDetectorRef, DoCheck } from "@angular/core";

@Component({
	selector: "common-scroll-to-float",
	templateUrl: "./scroll-to-float.component.html",
	styleUrls: ["./scroll-to-float.component.scss"],
	encapsulation: ViewEncapsulation.None,
})
export class ScrollToFloatComponent implements OnInit {
	public shown = false;
	@Input()
	public target: string = "";

	ngOnInit(): void {
		window.addEventListener("scroll",  ()=>this.updateShownStatus());
	}

	updateShownStatus() {
		this.shown = window.scrollY > 50;
	}
}
