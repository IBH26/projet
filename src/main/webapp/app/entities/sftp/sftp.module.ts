import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { SFTPComponent } from './sftp.component';
import { SFTPDetailComponent } from './sftp-detail.component';
import { SFTPUpdateComponent } from './sftp-update.component';
import { SFTPDeleteDialogComponent } from './sftp-delete-dialog.component';
import { sFTPRoute } from './sftp.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(sFTPRoute)],
  declarations: [SFTPComponent, SFTPDetailComponent, SFTPUpdateComponent, SFTPDeleteDialogComponent],
  entryComponents: [SFTPDeleteDialogComponent]
})
export class AppSFTPModule {}
