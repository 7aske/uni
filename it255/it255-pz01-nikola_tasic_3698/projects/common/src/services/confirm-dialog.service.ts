import { Injectable } from "@angular/core";
import { Subject, Observable } from "rxjs";

export interface Dialog {
	type: "confirm" | undefined;
	text: string;

	confirm: () => void;

	reject: () => void;
}

@Injectable({
	providedIn: "root",
})
export class ConfirmDialogService {
	private subject = new Subject<Dialog>();

	public confirm(message: string, confirm?: () => void, reject?: () => void): any {
		this.setConfirmation(message, confirm, reject);
	}

	private setConfirmation(message: string, confirm?: () => void, reject?: () => void): any {
		this.subject.next({
			type: "confirm",
			text: message,
			confirm: () => {
				this.subject.next();
				if (confirm) confirm();
			},
			reject: () => {
				this.subject.next();
				if (reject) reject();
			},
		});

	}

	public getDialog(): Observable<Dialog> {
		return this.subject.asObservable();
	}
}
