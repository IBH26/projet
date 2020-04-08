import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContactTechnique } from 'app/shared/model/contact-technique.model';
import { ContactTechniqueService } from './contact-technique.service';

@Component({
  templateUrl: './contact-technique-delete-dialog.component.html'
})
export class ContactTechniqueDeleteDialogComponent {
  contactTechnique?: IContactTechnique;

  constructor(
    protected contactTechniqueService: ContactTechniqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contactTechniqueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contactTechniqueListModification');
      this.activeModal.close();
    });
  }
}
