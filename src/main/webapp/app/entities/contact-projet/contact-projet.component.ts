import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContactProjet } from 'app/shared/model/contact-projet.model';
import { ContactProjetService } from './contact-projet.service';
import { ContactProjetDeleteDialogComponent } from './contact-projet-delete-dialog.component';

@Component({
  selector: 'jhi-contact-projet',
  templateUrl: './contact-projet.component.html'
})
export class ContactProjetComponent implements OnInit, OnDestroy {
  contactProjets?: IContactProjet[];
  eventSubscriber?: Subscription;

  constructor(
    protected contactProjetService: ContactProjetService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.contactProjetService.query().subscribe((res: HttpResponse<IContactProjet[]>) => (this.contactProjets = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContactProjets();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContactProjet): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContactProjets(): void {
    this.eventSubscriber = this.eventManager.subscribe('contactProjetListModification', () => this.loadAll());
  }

  delete(contactProjet: IContactProjet): void {
    const modalRef = this.modalService.open(ContactProjetDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contactProjet = contactProjet;
  }
}
