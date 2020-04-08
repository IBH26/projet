import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISFTP, SFTP } from 'app/shared/model/sftp.model';
import { SFTPService } from './sftp.service';

@Component({
  selector: 'jhi-sftp-update',
  templateUrl: './sftp-update.component.html'
})
export class SFTPUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    formatTransfer: [],
    user: [],
    key: []
  });

  constructor(protected sFTPService: SFTPService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sFTP }) => {
      this.updateForm(sFTP);
    });
  }

  updateForm(sFTP: ISFTP): void {
    this.editForm.patchValue({
      id: sFTP.id,
      formatTransfer: sFTP.formatTransfer,
      user: sFTP.user,
      key: sFTP.key
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sFTP = this.createFromForm();
    if (sFTP.id !== undefined) {
      this.subscribeToSaveResponse(this.sFTPService.update(sFTP));
    } else {
      this.subscribeToSaveResponse(this.sFTPService.create(sFTP));
    }
  }

  private createFromForm(): ISFTP {
    return {
      ...new SFTP(),
      id: this.editForm.get(['id'])!.value,
      formatTransfer: this.editForm.get(['formatTransfer'])!.value,
      user: this.editForm.get(['user'])!.value,
      key: this.editForm.get(['key'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISFTP>>): void {
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
