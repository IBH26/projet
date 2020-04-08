import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPESIT } from 'app/shared/model/pesit.model';
import { PESITService } from './pesit.service';

@Component({
  templateUrl: './pesit-delete-dialog.component.html'
})
export class PESITDeleteDialogComponent {
  pESIT?: IPESIT;

  constructor(protected pESITService: PESITService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pESITService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pESITListModification');
      this.activeModal.close();
    });
  }
}
