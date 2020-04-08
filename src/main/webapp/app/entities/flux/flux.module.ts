import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { FluxComponent } from './flux.component';
import { FluxDetailComponent } from './flux-detail.component';
import { FluxUpdateComponent } from './flux-update.component';
import { FluxDeleteDialogComponent } from './flux-delete-dialog.component';
import { fluxRoute } from './flux.route';
import { SearchFluxComponent } from './search-flux/search-flux.component';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(fluxRoute)],
  declarations: [FluxComponent, FluxDetailComponent, FluxUpdateComponent, FluxDeleteDialogComponent, SearchFluxComponent],
  entryComponents: [FluxDeleteDialogComponent]
})
export class AppFluxModule {}
