import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContactTechnique, ContactTechnique } from 'app/shared/model/contact-technique.model';
import { ContactTechniqueService } from './contact-technique.service';
import { ContactTechniqueComponent } from './contact-technique.component';
import { ContactTechniqueDetailComponent } from './contact-technique-detail.component';
import { ContactTechniqueUpdateComponent } from './contact-technique-update.component';

@Injectable({ providedIn: 'root' })
export class ContactTechniqueResolve implements Resolve<IContactTechnique> {
  constructor(private service: ContactTechniqueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContactTechnique> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((contactTechnique: HttpResponse<ContactTechnique>) => {
          if (contactTechnique.body) {
            return of(contactTechnique.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ContactTechnique());
  }
}

export const contactTechniqueRoute: Routes = [
  {
    path: '',
    component: ContactTechniqueComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactTechnique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ContactTechniqueDetailComponent,
    resolve: {
      contactTechnique: ContactTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactTechnique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ContactTechniqueUpdateComponent,
    resolve: {
      contactTechnique: ContactTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactTechnique.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ContactTechniqueUpdateComponent,
    resolve: {
      contactTechnique: ContactTechniqueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.contactTechnique.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
