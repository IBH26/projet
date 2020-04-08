import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDemandeur } from 'app/shared/model/demandeur.model';
import { DemandeurService } from './demandeur.service';

@Component({
  templateUrl: './demandeur-delete-dialog.component.html'
})
export class DemandeurDeleteDialogComponent {
  demandeur?: IDemandeur;

  constructor(protected demandeurService: DemandeurService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.demandeurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('demandeurListModification');
      this.activeModal.close();
    });
  }
}
