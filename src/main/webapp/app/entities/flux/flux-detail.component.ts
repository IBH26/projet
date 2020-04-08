import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFlux } from 'app/shared/model/flux.model';

@Component({
  selector: 'jhi-flux-detail',
  templateUrl: './flux-detail.component.html'
})
export class FluxDetailComponent implements OnInit {
  flux: IFlux | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ flux }) => (this.flux = flux));
  }

  previousState(): void {
    window.history.back();
  }
}
