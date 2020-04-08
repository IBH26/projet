import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPESIT } from 'app/shared/model/pesit.model';

@Component({
  selector: 'jhi-pesit-detail',
  templateUrl: './pesit-detail.component.html'
})
export class PESITDetailComponent implements OnInit {
  pESIT: IPESIT | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pESIT }) => (this.pESIT = pESIT));
  }

  previousState(): void {
    window.history.back();
  }
}
