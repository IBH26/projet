import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ContactProjetComponent } from './contact-projet.component';
import { ContactProjetDetailComponent } from './contact-projet-detail.component';
import { ContactProjetUpdateComponent } from './contact-projet-update.component';
import { ContactProjetDeleteDialogComponent } from './contact-projet-delete-dialog.component';
import { contactProjetRoute } from './contact-projet.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(contactProjetRoute)],
  declarations: [ContactProjetComponent, ContactProjetDetailComponent, ContactProjetUpdateComponent, ContactProjetDeleteDialogComponent],
  entryComponents: [ContactProjetDeleteDialogComponent]
})
export class AppContactProjetModule {}
