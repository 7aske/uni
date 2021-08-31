import { Component, OnInit } from '@angular/core';
import { Tag } from "../../../../common/src/@types/entity/Tag";
import { FormGroup, FormControl } from "@angular/forms";
import { TagService } from "../../services/tag.service";
import { ConfirmDialogService } from "../../../../common/src/services/confirm-dialog.service";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'admin-edit-tag',
  templateUrl: './edit-tag.component.html',
  styleUrls: ['./edit-tag.component.scss']
})
export class EditTagComponent implements OnInit {

  public tags: Tag[] = [];
  public tag?: Tag = {id: undefined, name: ""};
  public tagForm = new FormGroup({
    "name": new FormControl(this.tag?.name),
  });

  constructor(private tagService: TagService,
              private confirmDialogService: ConfirmDialogService,
              private toastService: ToastrService) {
  }

  ngOnInit(): void {
    (async () => {
      this.tags = await this.tagService.getAll();
      setTimeout(() => this.initTagSelect(), 10);
    })();
  }

  public resetForm() {
    this.tag = {id: undefined, name: ""};
    setTimeout(() => this.initTagSelect(), 10);
    const selectInstance = M.FormSelect.getInstance(document.querySelector("select")!);
    selectInstance.input.value = (selectInstance.el as HTMLSelectElement).options[0].text;
    (selectInstance.el as HTMLSelectElement).selectedIndex = 0;
  }

  public saveOrUpdate() {
    this.tag!.name = this.tagForm.get("name")?.value;
    if (!this.tag) {
      this.toastService.error("No tag selected");
      return;
    }
    if (this.tag.id) {
      this.tagService.update(this.tag)
          .then(_tag => {
            this.tag = _tag;
            this.tags = this.tags.map(t => t.id === _tag.id ? _tag : t);
            this.toastService.info("Updated!");
            this.resetForm();
          })
          .catch(err => this.toastService.error(err.error.error));
    } else {
      this.tagService.save(this.tag)
          .then(_tag => {
            this.tag = _tag;
            this.tags = [...this.tags, _tag];
            this.toastService.info("Saved!");
            this.resetForm();
          })
          .catch(err => this.toastService.error(err.error.error));
    }
  }

  public delete() {
    if (!this.tag || !this.tag.id) {
      this.toastService.error("No tag selected");
      return;
    }
    this.tagService.delete(this.tag.id!)
        .then(() => {
          this.tags = this.tags.filter(_tag => _tag.id !== this.tag!.id);
          this.toastService.info("Deleted!");
          this.resetForm();
        })
        .catch(err => this.toastService.error(err.error.error));
  }

  private initTagSelect() {
    M.FormSelect.init(document.querySelector("select")!, {
      dropdownOptions: {
        coverTrigger: true,
        closeOnClick: true,
        autoTrigger: true,
        onCloseEnd: (el) => {
          const target = el as HTMLSelectElement;
          this.tag = this.tags.find(tag => tag.name === target.value);
          setTimeout(() => M.updateTextFields(), 10);
        },
      },
    });
  }
}
