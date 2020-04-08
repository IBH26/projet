import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { PESITComponent } from './pesit.component';
import { PESITDetailComponent } from './pesit-detail.component';
import { PESITUpdateComponent } from './pesit-update.component';
import { PESITDeleteDialogComponent } from './pesit-delete-dialog.component';
import { pESITRoute } from './pesit.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(pESITRoute)],
  declarations: [PESITComponent, PESITDetailComponent, PESITUpdateComponent, PESITDeleteDialogComponent],
  entryComponents: [PESITDeleteDialogComponent]
})
export class AppPESITModule {}
