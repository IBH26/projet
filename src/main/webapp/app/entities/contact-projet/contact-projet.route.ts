import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContactProjet, ContactProjet } from 'app/shared/model/contact-projet.model';
import { ContactProjetService } from './contact-projet.service';
import { ContactProjetComponent } from './contact-projet.component';
import { ContactProjetDetailComponent } from './contact-projet-detail.component';
import { ContactProjetUpdateComponent } from './contact-projet-update.component';

@Injectable({ providedIn: 'root' })
export class ContactProjetResolve implements Resolve<IContactProjet> {
  constructor(private service: ContactProjetService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContactProjet> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((contactProjet: HttpResponse<ContactProjet>) => {
          if (contactProjet.body) {
            return of(contactProjet.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ContactProjet());
  }
}

export const contactProjetRoute: Routes = [
  {
    path: '',
    component: ContactProjetComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactProjet.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ContactProjetDetailComponent,
    resolve: {
      contactProjet: ContactProjetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactProjet.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ContactProjetUpdateComponent,
    resolve: {
      contactProjet: ContactProjetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactProjet.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ContactProjetUpdateComponent,
    resolve: {
      contactProjet: ContactProjetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactProjet.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
