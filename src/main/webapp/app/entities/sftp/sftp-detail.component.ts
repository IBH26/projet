import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISFTP } from 'app/shared/model/sftp.model';

@Component({
  selector: 'jhi-sftp-detail',
  templateUrl: './sftp-detail.component.html'
})
export class SFTPDetailComponent implements OnInit {
  sFTP: ISFTP | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sFTP }) => (this.sFTP = sFTP));
  }

  previousState(): void {
    window.history.back();
  }
}
