import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IROUTAGE } from 'app/shared/model/routage.model';
import { ROUTAGEService } from './routage.service';

@Component({
  templateUrl: './routage-delete-dialog.component.html'
})
export class ROUTAGEDeleteDialogComponent {
  rOUTAGE?: IROUTAGE;

  constructor(protected rOUTAGEService: ROUTAGEService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rOUTAGEService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rOUTAGEListModification');
      this.activeModal.close();
    });
  }
}
