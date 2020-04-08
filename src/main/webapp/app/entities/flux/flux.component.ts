import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFlux } from 'app/shared/model/flux.model';
import { FluxService } from './flux.service';
import { FluxDeleteDialogComponent } from './flux-delete-dialog.component';

@Component({
  selector: 'jhi-flux',
  templateUrl: './flux.component.html'
})
export class FluxComponent implements OnInit, OnDestroy {
  fluxes?: IFlux[];
  eventSubscriber?: Subscription;

  constructor(protected fluxService: FluxService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.fluxService.query().subscribe((res: HttpResponse<IFlux[]>) => (this.fluxes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFluxes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFlux): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFluxes(): void {
    this.eventSubscriber = this.eventManager.subscribe('fluxListModification', () => this.loadAll());
  }

  delete(flux: IFlux): void {
    const modalRef = this.modalService.open(FluxDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.flux = flux;
  }
}
