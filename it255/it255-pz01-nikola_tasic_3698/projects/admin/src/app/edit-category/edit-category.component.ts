import { Component, OnInit } from "@angular/core";
import { Category } from "../../../../common/src/@types/entity/Category";
import { CategoryService } from "../../services/category.service";
import { ConfirmDialogService } from "../../../../common/src/services/confirm-dialog.service";
import { ToastrService } from "ngx-toastr";
import { FormGroup, FormControl } from "@angular/forms";

@Component({
	selector: "admin-edit-category",
	templateUrl: "./edit-category.component.html",
	styleUrls: ["./edit-category.component.scss"],
})
export class EditCategoryComponent implements OnInit {

	public categories: Category[] = [];
	public category?: Category = {id: undefined, name: ""};
	public categoryForm = new FormGroup({
		"name": new FormControl(this.category?.name),
	});

	constructor(private categoryService: CategoryService,
	            private confirmDialogService: ConfirmDialogService,
	            private toastService: ToastrService) {
	}

	ngOnInit(): void {
		(async () => {
			this.categories = await this.categoryService.getAll();
			setTimeout(() => this.initCategorySelect(), 10);
		})();
	}

	public resetForm() {
		this.category = {id: undefined, name: ""};
		setTimeout(() => this.initCategorySelect(), 10);
		const selectInstance = M.FormSelect.getInstance(document.querySelector("select")!);
		selectInstance.input.value = (selectInstance.el as HTMLSelectElement).options[0].text;
		(selectInstance.el as HTMLSelectElement).selectedIndex = 0;
	}

	public saveOrUpdate() {
		this.category!.name = this.categoryForm.get("name")?.value;
		if (!this.category) {
			this.toastService.error("No category selected");
			return;
		}
		if (this.category.id) {
			this.categoryService.update(this.category)
				.then(_categ => {
					this.category = _categ;
					this.categories = this.categories.map(c => c.id === _categ.id ? _categ : c);
					this.toastService.info("Updated!");
					this.resetForm();
				})
				.catch(err => this.toastService.error(err.error.error));
		} else {
			this.categoryService.save(this.category)
				.then(_categ => {
					this.category = _categ;
					this.categories = [...this.categories, _categ];
					this.toastService.info("Saved!");
					this.resetForm();
				})
				.catch(err => this.toastService.error(err.error.error));
		}
	}

	public delete() {
		if (!this.category || !this.category.id) {
			this.toastService.error("No category selected");
			return;
		}
		this.categoryService.delete(this.category.id!)
			.then(() => {
				this.categories = this.categories.filter(_categ => _categ.id !== this.category!.id);
				this.toastService.info("Deleted!");
				this.resetForm();
			})
			.catch(err => this.toastService.error(err.error.error));
	}

	private initCategorySelect() {
		M.FormSelect.init(document.querySelector("select")!, {
			dropdownOptions: {
				coverTrigger: true,
				closeOnClick: true,
				autoTrigger: true,
				onCloseEnd: (el) => {
					const target = el as HTMLSelectElement;
					this.category = this.categories.find(categ => categ.name === target.value);
					setTimeout(() => M.updateTextFields(), 10);
				},
			},
		});
	}
}
