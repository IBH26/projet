import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContactProjet } from 'app/shared/model/contact-projet.model';

@Component({
  selector: 'jhi-contact-projet-detail',
  templateUrl: './contact-projet-detail.component.html'
})
export class ContactProjetDetailComponent implements OnInit {
  contactProjet: IContactProjet | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contactProjet }) => (this.contactProjet = contactProjet));
  }

  previousState(): void {
    window.history.back();
  }
}
