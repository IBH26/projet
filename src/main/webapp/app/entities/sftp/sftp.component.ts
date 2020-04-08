import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISFTP } from 'app/shared/model/sftp.model';
import { SFTPService } from './sftp.service';
import { SFTPDeleteDialogComponent } from './sftp-delete-dialog.component';

@Component({
  selector: 'jhi-sftp',
  templateUrl: './sftp.component.html'
})
export class SFTPComponent implements OnInit, OnDestroy {
  sFTPS?: ISFTP[];
  eventSubscriber?: Subscription;

  constructor(protected sFTPService: SFTPService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.sFTPService.query().subscribe((res: HttpResponse<ISFTP[]>) => (this.sFTPS = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSFTPS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISFTP): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSFTPS(): void {
    this.eventSubscriber = this.eventManager.subscribe('sFTPListModification', () => this.loadAll());
  }

  delete(sFTP: ISFTP): void {
    const modalRef = this.modalService.open(SFTPDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sFTP = sFTP;
  }
}
