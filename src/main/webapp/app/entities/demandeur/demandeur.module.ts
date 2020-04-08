import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { DemandeurComponent } from './demandeur.component';
import { DemandeurDetailComponent } from './demandeur-detail.component';
import { DemandeurUpdateComponent } from './demandeur-update.component';
import { DemandeurDeleteDialogComponent } from './demandeur-delete-dialog.component';
import { demandeurRoute } from './demandeur.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(demandeurRoute)],
  declarations: [DemandeurComponent, DemandeurDetailComponent, DemandeurUpdateComponent, DemandeurDeleteDialogComponent],
  entryComponents: [DemandeurDeleteDialogComponent]
})
export class AppDemandeurModule {}
