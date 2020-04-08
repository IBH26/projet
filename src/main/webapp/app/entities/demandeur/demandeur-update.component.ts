import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDemandeur, Demandeur } from 'app/shared/model/demandeur.model';
import { DemandeurService } from './demandeur.service';

@Component({
  selector: 'jhi-demandeur-update',
  templateUrl: './demandeur-update.component.html'
})
export class DemandeurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameD: [null, [Validators.required]],
    functionD: [null, [Validators.required]],
    projectD: []
  });

  constructor(protected demandeurService: DemandeurService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demandeur }) => {
      this.updateForm(demandeur);
    });
  }

  updateForm(demandeur: IDemandeur): void {
    this.editForm.patchValue({
      id: demandeur.id,
      nameD: demandeur.nameD,
      functionD: demandeur.functionD,
      projectD: demandeur.projectD
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demandeur = this.createFromForm();
    if (demandeur.id !== undefined) {
      this.subscribeToSaveResponse(this.demandeurService.update(demandeur));
    } else {
      this.subscribeToSaveResponse(this.demandeurService.create(demandeur));
    }
  }

  private createFromForm(): IDemandeur {
    return {
      ...new Demandeur(),
      id: this.editForm.get(['id'])!.value,
      nameD: this.editForm.get(['nameD'])!.value,
      functionD: this.editForm.get(['functionD'])!.value,
      projectD: this.editForm.get(['projectD'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemandeur>>): void {
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
