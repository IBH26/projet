import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IContactTechnique } from 'app/shared/model/contact-technique.model';
import { ContactTechniqueService } from './contact-technique.service';
import { ContactTechniqueDeleteDialogComponent } from './contact-technique-delete-dialog.component';

@Component({
  selector: 'jhi-contact-technique',
  templateUrl: './contact-technique.component.html'
})
export class ContactTechniqueComponent implements OnInit, OnDestroy {
  contactTechniques?: IContactTechnique[];
  eventSubscriber?: Subscription;

  constructor(
    protected contactTechniqueService: ContactTechniqueService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.contactTechniqueService.query().subscribe((res: HttpResponse<IContactTechnique[]>) => (this.contactTechniques = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInContactTechniques();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContactTechnique): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContactTechniques(): void {
    this.eventSubscriber = this.eventManager.subscribe('contactTechniqueListModification', () => this.loadAll());
  }

  delete(contactTechnique: IContactTechnique): void {
    const modalRef = this.modalService.open(ContactTechniqueDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contactTechnique = contactTechnique;
  }
}
