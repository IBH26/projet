import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ContactTechniqueComponent } from './contact-technique.component';
import { ContactTechniqueDetailComponent } from './contact-technique-detail.component';
import { ContactTechniqueUpdateComponent } from './contact-technique-update.component';
import { ContactTechniqueDeleteDialogComponent } from './contact-technique-delete-dialog.component';
import { contactTechniqueRoute } from './contact-technique.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(contactTechniqueRoute)],
  declarations: [
    ContactTechniqueComponent,
    ContactTechniqueDetailComponent,
    ContactTechniqueUpdateComponent,
    ContactTechniqueDeleteDialogComponent
  ],
  entryComponents: [ContactTechniqueDeleteDialogComponent]
})
export class AppContactTechniqueModule {}
