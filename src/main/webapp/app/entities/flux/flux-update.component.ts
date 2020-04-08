import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFlux, Flux } from 'app/shared/model/flux.model';
import { FluxService } from './flux.service';

@Component({
  selector: 'jhi-flux-update',
  templateUrl: './flux-update.component.html'
})
export class FluxUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeMega: [null, [Validators.required]],
    envir: []
  });

  constructor(protected fluxService: FluxService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ flux }) => {
      this.updateForm(flux);
    });
  }

  updateForm(flux: IFlux): void {
    this.editForm.patchValue({
      id: flux.id,
      codeMega: flux.codeMega,
      envir: flux.envir
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const flux = this.createFromForm();
    if (flux.id !== undefined) {
      this.subscribeToSaveResponse(this.fluxService.update(flux));
    } else {
      this.subscribeToSaveResponse(this.fluxService.create(flux));
    }
  }

  private createFromForm(): IFlux {
    return {
      ...new Flux(),
      id: this.editForm.get(['id'])!.value,
      codeMega: this.editForm.get(['codeMega'])!.value,
      envir: this.editForm.get(['envir'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFlux>>): void {
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
