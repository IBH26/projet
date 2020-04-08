import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContactProjet, ContactProjet } from 'app/shared/model/contact-projet.model';
import { ContactProjetService } from './contact-projet.service';

@Component({
  selector: 'jhi-contact-projet-update',
  templateUrl: './contact-projet-update.component.html'
})
export class ContactProjetUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameCP: [null, [Validators.required]],
    functionCP: [null, [Validators.required]],
    phoneNumberCP: [],
    emailCP: []
  });

  constructor(protected contactProjetService: ContactProjetService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactProjet }) => {
      this.updateForm(contactProjet);
    });
  }

  updateForm(contactProjet: IContactProjet): void {
    this.editForm.patchValue({
      id: contactProjet.id,
      nameCP: contactProjet.nameCP,
      functionCP: contactProjet.functionCP,
      phoneNumberCP: contactProjet.phoneNumberCP,
      emailCP: contactProjet.emailCP
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contactProjet = this.createFromForm();
    if (contactProjet.id !== undefined) {
      this.subscribeToSaveResponse(this.contactProjetService.update(contactProjet));
    } else {
      this.subscribeToSaveResponse(this.contactProjetService.create(contactProjet));
    }
  }

  private createFromForm(): IContactProjet {
    return {
      ...new ContactProjet(),
      id: this.editForm.get(['id'])!.value,
      nameCP: this.editForm.get(['nameCP'])!.value,
      functionCP: this.editForm.get(['functionCP'])!.value,
      phoneNumberCP: this.editForm.get(['phoneNumberCP'])!.value,
      emailCP: this.editForm.get(['emailCP'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContactProjet>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
