import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISFTP } from 'app/shared/model/sftp.model';
import { SFTPService } from './sftp.service';

@Component({
  templateUrl: './sftp-delete-dialog.component.html'
})
export class SFTPDeleteDialogComponent {
  sFTP?: ISFTP;

  constructor(protected sFTPService: SFTPService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sFTPService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sFTPListModification');
      this.activeModal.close();
    });
  }
}
