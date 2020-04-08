import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IROUTAGE, ROUTAGE } from 'app/shared/model/routage.model';
import { ROUTAGEService } from './routage.service';
import { ROUTAGEComponent } from './routage.component';
import { ROUTAGEDetailComponent } from './routage-detail.component';
import { ROUTAGEUpdateComponent } from './routage-update.component';

@Injectable({ providedIn: 'root' })
export class ROUTAGEResolve implements Resolve<IROUTAGE> {
  constructor(private service: ROUTAGEService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IROUTAGE> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rOUTAGE: HttpResponse<ROUTAGE>) => {
          if (rOUTAGE.body) {
            return of(rOUTAGE.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ROUTAGE());
  }
}

export const rOUTAGERoute: Routes = [
  {
    path: '',
    component: ROUTAGEComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.rOUTAGE.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ROUTAGEDetailComponent,
    resolve: {
      rOUTAGE: ROUTAGEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.rOUTAGE.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ROUTAGEUpdateComponent,
    resolve: {
      rOUTAGE: ROUTAGEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.rOUTAGE.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ROUTAGEUpdateComponent,
    resolve: {
      rOUTAGE: ROUTAGEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.rOUTAGE.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
