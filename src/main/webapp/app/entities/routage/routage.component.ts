import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IROUTAGE } from 'app/shared/model/routage.model';
import { ROUTAGEService } from './routage.service';
import { ROUTAGEDeleteDialogComponent } from './routage-delete-dialog.component';

@Component({
  selector: 'jhi-routage',
  templateUrl: './routage.component.html'
})
export class ROUTAGEComponent implements OnInit, OnDestroy {
  rOUTAGES?: IROUTAGE[];
  eventSubscriber?: Subscription;

  constructor(protected rOUTAGEService: ROUTAGEService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.rOUTAGEService.query().subscribe((res: HttpResponse<IROUTAGE[]>) => (this.rOUTAGES = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInROUTAGES();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IROUTAGE): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInROUTAGES(): void {
    this.eventSubscriber = this.eventManager.subscribe('rOUTAGEListModification', () => this.loadAll());
  }

  delete(rOUTAGE: IROUTAGE): void {
    const modalRef = this.modalService.open(ROUTAGEDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rOUTAGE = rOUTAGE;
  }
}
