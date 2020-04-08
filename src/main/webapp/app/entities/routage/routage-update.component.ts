import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IROUTAGE, ROUTAGE } from 'app/shared/model/routage.model';
import { ROUTAGEService } from './routage.service';
import { IDemiFlux } from 'app/shared/model/demi-flux.model';
import { DemiFluxService } from 'app/entities/demi-flux/demi-flux.service';

@Component({
  selector: 'jhi-routage-update',
  templateUrl: './routage-update.component.html'
})
export class ROUTAGEUpdateComponent implements OnInit {
  isSaving = false;
  demifluxes: IDemiFlux[] = [];

  editForm = this.fb.group({
    id: [],
    drName: [null, [Validators.required]],
    rename: [],
    maskFile: [],
    directory: [],
    handlingtype: [],
    demiflux: []
  });

  constructor(
    protected rOUTAGEService: ROUTAGEService,
    protected demiFluxService: DemiFluxService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rOUTAGE }) => {
      this.updateForm(rOUTAGE);

      this.demiFluxService.query().subscribe((res: HttpResponse<IDemiFlux[]>) => (this.demifluxes = res.body || []));
    });
  }

  updateForm(rOUTAGE: IROUTAGE): void {
    this.editForm.patchValue({
      id: rOUTAGE.id,
      drName: rOUTAGE.drName,
      rename: rOUTAGE.rename,
      maskFile: rOUTAGE.maskFile,
      directory: rOUTAGE.directory,
      handlingtype: rOUTAGE.handlingtype,
      demiflux: rOUTAGE.demiflux
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rOUTAGE = this.createFromForm();
    if (rOUTAGE.id !== undefined) {
      this.subscribeToSaveResponse(this.rOUTAGEService.update(rOUTAGE));
    } else {
      this.subscribeToSaveResponse(this.rOUTAGEService.create(rOUTAGE));
    }
  }

  private createFromForm(): IROUTAGE {
    return {
      ...new ROUTAGE(),
      id: this.editForm.get(['id'])!.value,
      drName: this.editForm.get(['drName'])!.value,
      rename: this.editForm.get(['rename'])!.value,
      maskFile: this.editForm.get(['maskFile'])!.value,
      directory: this.editForm.get(['directory'])!.value,
      handlingtype: this.editForm.get(['handlingtype'])!.value,
      demiflux: this.editForm.get(['demiflux'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IROUTAGE>>): void {
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

  trackById(index: number, item: IDemiFlux): any {
    return item.id;
  }
}
