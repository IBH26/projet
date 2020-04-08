import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFlux, Flux } from 'app/shared/model/flux.model';
import { FluxService } from './flux.service';
import { FluxComponent } from './flux.component';
import { FluxDetailComponent } from './flux-detail.component';
import { FluxUpdateComponent } from './flux-update.component';
import { SearchFluxComponent } from './search-flux/search-flux.component';

@Injectable({ providedIn: 'root' })
export class FluxResolve implements Resolve<IFlux> {
  constructor(private service: FluxService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFlux> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((flux: HttpResponse<Flux>) => {
          if (flux.body) {
            return of(flux.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Flux());
  }
}

export const fluxRoute: Routes = [
  {
    path: '',
    component: FluxComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.flux.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FluxDetailComponent,
    resolve: {
      flux: FluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.flux.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FluxUpdateComponent,
    resolve: {
      flux: FluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.flux.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FluxUpdateComponent,
    resolve: {
      flux: FluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.flux.home.title'
    },
    canActivate: [UserRouteAccessService]
  } ,

  {
    path: 'search',
    component: SearchFluxComponent,
    resolve: {
      flux: FluxResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'projetFilhubApp.flux.home.title'
    },
    canActivate: [UserRouteAccessService]
  }

];
