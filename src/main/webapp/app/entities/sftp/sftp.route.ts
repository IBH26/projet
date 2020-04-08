import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISFTP, SFTP } from 'app/shared/model/sftp.model';
import { SFTPService } from './sftp.service';
import { SFTPComponent } from './sftp.component';
import { SFTPDetailComponent } from './sftp-detail.component';
import { SFTPUpdateComponent } from './sftp-update.component';

@Injectable({ providedIn: 'root' })
export class SFTPResolve implements Resolve<ISFTP> {
  constructor(private service: SFTPService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISFTP> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sFTP: HttpResponse<SFTP>) => {
          if (sFTP.body) {
            return of(sFTP.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SFTP());
  }
}

export const sFTPRoute: Routes = [
  {
    path: '',
    component: SFTPComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.sFTP.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SFTPDetailComponent,
    resolve: {
      sFTP: SFTPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.sFTP.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SFTPUpdateComponent,
    resolve: {
      sFTP: SFTPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.sFTP.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SFTPUpdateComponent,
    resolve: {
      sFTP: SFTPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'appApp.sFTP.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
