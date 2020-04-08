import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContactProjet } from 'app/shared/model/contact-projet.model';
import { ContactProjetService } from './contact-projet.service';

@Component({
  templateUrl: './contact-projet-delete-dialog.component.html'
})
export class ContactProjetDeleteDialogComponent {
  contactProjet?: IContactProjet;

  constructor(
    protected contactProjetService: ContactProjetService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contactProjetService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contactProjetListModification');
      this.activeModal.close();
    });
  }
}
