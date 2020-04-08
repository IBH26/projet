import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPESIT } from 'app/shared/model/pesit.model';
import { PESITService } from './pesit.service';
import { PESITDeleteDialogComponent } from './pesit-delete-dialog.component';

@Component({
  selector: 'jhi-pesit',
  templateUrl: './pesit.component.html'
})
export class PESITComponent implements OnInit, OnDestroy {
  pESITS?: IPESIT[];
  eventSubscriber?: Subscription;

  constructor(protected pESITService: PESITService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.pESITService.query().subscribe((res: HttpResponse<IPESIT[]>) => (this.pESITS = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPESITS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPESIT): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPESITS(): void {
    this.eventSubscriber = this.eventManager.subscribe('pESITListModification', () => this.loadAll());
  }

  delete(pESIT: IPESIT): void {
    const modalRef = this.modalService.open(PESITDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pESIT = pESIT;
  }
}
