import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDemandeur } from 'app/shared/model/demandeur.model';
import { DemandeurService } from './demandeur.service';
import { DemandeurDeleteDialogComponent } from './demandeur-delete-dialog.component';

@Component({
  selector: 'jhi-demandeur',
  templateUrl: './demandeur.component.html'
})
export class DemandeurComponent implements OnInit, OnDestroy {
  demandeurs?: IDemandeur[];
  eventSubscriber?: Subscription;

  constructor(protected demandeurService: DemandeurService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.demandeurService.query().subscribe((res: HttpResponse<IDemandeur[]>) => (this.demandeurs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDemandeurs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDemandeur): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDemandeurs(): void {
    this.eventSubscriber = this.eventManager.subscribe('demandeurListModification', () => this.loadAll());
  }

  delete(demandeur: IDemandeur): void {
    const modalRef = this.modalService.open(DemandeurDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.demandeur = demandeur;
  }
}
