import { Component, OnInit } from "@angular/core";
import { ConfirmDialogService, Dialog } from "../../services/confirm-dialog.service";

@Component({
	selector: "common-confirm-dialog",
	templateUrl: "./confirm-dialog.component.html",
	styleUrls: ["./confirm-dialog.component.scss"],
})
export class ConfirmDialogComponent implements OnInit {
	dialog: Dialog;

	constructor(private confirmDialogService: ConfirmDialogService) {
	}

	ngOnInit(): any {
		this.confirmDialogService.getDialog().subscribe(dialog => {
			this.dialog = dialog;
		});
	}
}
