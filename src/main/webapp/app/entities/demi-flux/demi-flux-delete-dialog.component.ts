import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemiFlux } from 'app/shared/model/demi-flux.model';
import { DemiFluxService } from './demi-flux.service';

@Component({
  templateUrl: './demi-flux-delete-dialog.component.html'
})
export class DemiFluxDeleteDialogComponent {
  demiFlux?: IDemiFlux;

  constructor(protected demiFluxService: DemiFluxService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.demiFluxService.delete(id).subscribe(() => {
      this.eventManager.broadcast('demiFluxListModification');
      this.activeModal.close();
    });
  }
}
