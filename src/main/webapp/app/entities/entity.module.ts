import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'contact-projet',
        loadChildren: () => import('./contact-projet/contact-projet.module').then(m => m.AppContactProjetModule)
      },
      {
        path: 'contact-technique',
        loadChildren: () => import('./contact-technique/contact-technique.module').then(m => m.AppContactTechniqueModule)
      },
      {
        path: 'demandeur',
        loadChildren: () => import('./demandeur/demandeur.module').then(m => m.AppDemandeurModule)
      },
      {
        path: 'flux',
        loadChildren: () => import('./flux/flux.module').then(m => m.AppFluxModule)
      },
      {
        path: 'demi-flux',
        loadChildren: () => import('./demi-flux/demi-flux.module').then(m => m.AppDemiFluxModule)
      },
      {
        path: 'sftp',
        loadChildren: () => import('./sftp/sftp.module').then(m => m.AppSFTPModule)
      },
      {
        path: 'pesit',
        loadChildren: () => import('./pesit/pesit.module').then(m => m.AppPESITModule)
      },
      {
        path: 'routage',
        loadChildren: () => import('./routage/routage.module').then(m => m.AppROUTAGEModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class AppEntityModule {}
