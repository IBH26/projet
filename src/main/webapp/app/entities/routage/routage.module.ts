import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ROUTAGEComponent } from './routage.component';
import { ROUTAGEDetailComponent } from './routage-detail.component';
import { ROUTAGEUpdateComponent } from './routage-update.component';
import { ROUTAGEDeleteDialogComponent } from './routage-delete-dialog.component';
import { rOUTAGERoute } from './routage.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(rOUTAGERoute)],
  declarations: [ROUTAGEComponent, ROUTAGEDetailComponent, ROUTAGEUpdateComponent, ROUTAGEDeleteDialogComponent],
  entryComponents: [ROUTAGEDeleteDialogComponent]
})
export class AppROUTAGEModule {}
