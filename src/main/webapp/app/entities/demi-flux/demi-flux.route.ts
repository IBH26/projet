import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDemiFlux, DemiFlux } from 'app/shared/model/demi-flux.model';
import { DemiFluxService } from './demi-flux.service';
import { DemiFluxComponent } from './demi-flux.component';
import { DemiFluxDetailComponent } from './demi-flux-detail.component';
import { DemiFluxUpdateComponent } from './demi-flux-update.component';

@Injectable({ providedIn: 'root' })
export class DemiFluxResolve implements Resolve<IDemiFlux> {
  constructor(private service: DemiFluxService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDemiFlux> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((demiFlux: HttpResponse<DemiFlux>) => {
          if (demiFlux.body) {
            return of(demiFlux.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DemiFlux());
  }
}

export const demiFluxRoute: Routes = [
  {
    path: '',
    component: DemiFluxComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'appApp.demiFlux.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DemiFluxDetailComponent,
    resolve: {
      demiFlux: DemiFluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demiFlux.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DemiFluxUpdateComponent,
    resolve: {
      demiFlux: DemiFluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demiFlux.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DemiFluxUpdateComponent,
    resolve: {
      demiFlux: DemiFluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.demiFlux.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
