import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IROUTAGE } from 'app/shared/model/routage.model';

@Component({
  selector: 'jhi-routage-detail',
  templateUrl: './routage-detail.component.html'
})
export class ROUTAGEDetailComponent implements OnInit {
  rOUTAGE: IROUTAGE | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rOUTAGE }) => (this.rOUTAGE = rOUTAGE));
  }

  previousState(): void {
    window.history.back();
  }
}
