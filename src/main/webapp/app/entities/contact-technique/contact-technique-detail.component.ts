import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContactTechnique } from 'app/shared/model/contact-technique.model';

@Component({
  selector: 'jhi-contact-technique-detail',
  templateUrl: './contact-technique-detail.component.html'
})
export class ContactTechniqueDetailComponent implements OnInit {
  contactTechnique: IContactTechnique | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactTechnique }) => (this.contactTechnique = contactTechnique));
  }

  previousState(): void {
    window.history.back();
  }
}
