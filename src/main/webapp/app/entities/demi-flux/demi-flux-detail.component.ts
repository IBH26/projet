import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDemiFlux } from 'app/shared/model/demi-flux.model';

@Component({
  selector: 'jhi-demi-flux-detail',
  templateUrl: './demi-flux-detail.component.html'
})
export class DemiFluxDetailComponent implements OnInit {
  demiFlux: IDemiFlux | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demiFlux }) => (this.demiFlux = demiFlux));
  }

  previousState(): void {
    window.history.back();
  }
}
