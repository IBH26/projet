import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFlux } from 'app/shared/model/flux.model';
import { FluxService } from './flux.service';

@Component({
  templateUrl: './flux-delete-dialog.component.html'
})
export class FluxDeleteDialogComponent {
  flux?: IFlux;

  constructor(protected fluxService: FluxService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fluxService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fluxListModification');
      this.activeModal.close();
    });
  }
}
