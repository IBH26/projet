import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContactTechnique, ContactTechnique } from 'app/shared/model/contact-technique.model';
import { ContactTechniqueService } from './contact-technique.service';

@Component({
  selector: 'jhi-contact-technique-update',
  templateUrl: './contact-technique-update.component.html'
})
export class ContactTechniqueUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameCT: [null, [Validators.required]],
    functionCT: [null, [Validators.required]],
    phoneNumberCT: [],
    emailCT: []
  });

  constructor(
    protected contactTechniqueService: ContactTechniqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactTechnique }) => {
      this.updateForm(contactTechnique);
    });
  }

  updateForm(contactTechnique: IContactTechnique): void {
    this.editForm.patchValue({
      id: contactTechnique.id,
      nameCT: contactTechnique.nameCT,
      functionCT: contactTechnique.functionCT,
      phoneNumberCT: contactTechnique.phoneNumberCT,
      emailCT: contactTechnique.emailCT
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contactTechnique = this.createFromForm();
    if (contactTechnique.id !== undefined) {
      this.subscribeToSaveResponse(this.contactTechniqueService.update(contactTechnique));
    } else {
      this.subscribeToSaveResponse(this.contactTechniqueService.create(contactTechnique));
    }
  }

  private createFromForm(): IContactTechnique {
    return {
      ...new ContactTechnique(),
      id: this.editForm.get(['id'])!.value,
      nameCT: this.editForm.get(['nameCT'])!.value,
      functionCT: this.editForm.get(['functionCT'])!.value,
      phoneNumberCT: this.editForm.get(['phoneNumberCT'])!.value,
      emailCT: this.editForm.get(['emailCT'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContactTechnique>>): void {
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
