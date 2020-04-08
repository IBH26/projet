import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDemandeur, Demandeur } from 'app/shared/model/demandeur.model';
import { DemandeurService } from './demandeur.service';
import { DemandeurComponent } from './demandeur.component';
import { DemandeurDetailComponent } from './demandeur-detail.component';
import { DemandeurUpdateComponent } from './demandeur-update.component';

@Injectable({ providedIn: 'root' })
export class DemandeurResolve implements Resolve<IDemandeur> {
  constructor(private service: DemandeurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDemandeur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((demandeur: HttpResponse<Demandeur>) => {
          if (demandeur.body) {
            return of(demandeur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Demandeur());
  }
}

export const demandeurRoute: Routes = [
  {
    path: '',
    component: DemandeurComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demandeur.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DemandeurDetailComponent,
    resolve: {
      demandeur: DemandeurResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demandeur.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DemandeurUpdateComponent,
    resolve: {
      demandeur: DemandeurResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demandeur.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DemandeurUpdateComponent,
    resolve: {
      demandeur: DemandeurResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demandeur.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
