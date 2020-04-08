import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPESIT, PESIT } from 'app/shared/model/pesit.model';
import { PESITService } from './pesit.service';
import { PESITComponent } from './pesit.component';
import { PESITDetailComponent } from './pesit-detail.component';
import { PESITUpdateComponent } from './pesit-update.component';

@Injectable({ providedIn: 'root' })
export class PESITResolve implements Resolve<IPESIT> {
  constructor(private service: PESITService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPESIT> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pESIT: HttpResponse<PESIT>) => {
          if (pESIT.body) {
            return of(pESIT.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PESIT());
  }
}

export const pESITRoute: Routes = [
  {
    path: '',
    component: PESITComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.pESIT.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PESITDetailComponent,
    resolve: {
      pESIT: PESITResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.pESIT.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PESITUpdateComponent,
    resolve: {
      pESIT: PESITResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.pESIT.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PESITUpdateComponent,
    resolve: {
      pESIT: PESITResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.pESIT.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
