import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { DemiFluxComponent } from './demi-flux.component';
import { DemiFluxDetailComponent } from './demi-flux-detail.component';
import { DemiFluxUpdateComponent } from './demi-flux-update.component';
import { DemiFluxDeleteDialogComponent } from './demi-flux-delete-dialog.component';
import { demiFluxRoute } from './demi-flux.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(demiFluxRoute)],
  declarations: [DemiFluxComponent, DemiFluxDetailComponent, DemiFluxUpdateComponent, DemiFluxDeleteDialogComponent],
  entryComponents: [DemiFluxDeleteDialogComponent]
})
export class AppDemiFluxModule {}
