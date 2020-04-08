import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPESIT, PESIT } from 'app/shared/model/pesit.model';
import { PESITService } from './pesit.service';

@Component({
  selector: 'jhi-pesit-update',
  templateUrl: './pesit-update.component.html'
})
export class PESITUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeSite: [],
    codeApplication: [],
    transcoding: [],
    compression: []
  });

  constructor(protected pESITService: PESITService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pESIT }) => {
      this.updateForm(pESIT);
    });
  }

  updateForm(pESIT: IPESIT): void {
    this.editForm.patchValue({
      id: pESIT.id,
      codeSite: pESIT.codeSite,
      codeApplication: pESIT.codeApplication,
      transcoding: pESIT.transcoding,
      compression: pESIT.compression
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pESIT = this.createFromForm();
    if (pESIT.id !== undefined) {
      this.subscribeToSaveResponse(this.pESITService.update(pESIT));
    } else {
      this.subscribeToSaveResponse(this.pESITService.create(pESIT));
    }
  }

  private createFromForm(): IPESIT {
    return {
      ...new PESIT(),
      id: this.editForm.get(['id'])!.value,
      codeSite: this.editForm.get(['codeSite'])!.value,
      codeApplication: this.editForm.get(['codeApplication'])!.value,
      transcoding: this.editForm.get(['transcoding'])!.value,
      compression: this.editForm.get(['compression'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPESIT>>): void {
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
