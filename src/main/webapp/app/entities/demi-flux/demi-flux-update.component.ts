import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDemiFlux, DemiFlux } from 'app/shared/model/demi-flux.model';
import { DemiFluxService } from './demi-flux.service';
import { IContactProjet } from 'app/shared/model/contact-projet.model';
import { ContactProjetService } from 'app/entities/contact-projet/contact-projet.service';
import { IContactTechnique } from 'app/shared/model/contact-technique.model';
import { ContactTechniqueService } from 'app/entities/contact-technique/contact-technique.service';
import { IPESIT } from 'app/shared/model/pesit.model';
import { PESITService } from 'app/entities/pesit/pesit.service';
import { ISFTP } from 'app/shared/model/sftp.model';
import { SFTPService } from 'app/entities/sftp/sftp.service';
import { IDemandeur } from 'app/shared/model/demandeur.model';
import { DemandeurService } from 'app/entities/demandeur/demandeur.service';
import { IFlux } from 'app/shared/model/flux.model';
import { FluxService } from 'app/entities/flux/flux.service';

type SelectableEntity = IContactProjet | IContactTechnique | IPESIT | ISFTP | IDemandeur | IFlux;

@Component({
  selector: 'jhi-demi-flux-update',
  templateUrl: './demi-flux-update.component.html'
})
export class DemiFluxUpdateComponent implements OnInit {
  isSaving = false;
  contactprojets: IContactProjet[] = [];
  contacttechniques: IContactTechnique[] = [];
  pesits: IPESIT[] = [];
  sftps: ISFTP[] = [];
  demandeurs: IDemandeur[] = [];
  fluxes: IFlux[] = [];

  editForm = this.fb.group({
    id: [],
    appliname: [],
    partenairelocal: [],
    partenairedistant: [],
    directory: [],
    filename: [],
    mode: [],
    typology: [],
    type: [],
    hostname: [],
    port: [],
    contactProjet: [],
    contactTechnique: [],
    pESIT: [],
    sFTP: [],
    demandeur: [null, Validators.required],
    flux: []
  });

  constructor(
    protected demiFluxService: DemiFluxService,
    protected contactProjetService: ContactProjetService,
    protected contactTechniqueService: ContactTechniqueService,
    protected pESITService: PESITService,
    protected sFTPService: SFTPService,
    protected demandeurService: DemandeurService,
    protected fluxService: FluxService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demiFlux }) => {
      this.updateForm(demiFlux);

      this.contactProjetService
        .query({ filter: 'demiflux-is-null' })
        .pipe(
          map((res: HttpResponse<IContactProjet[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IContactProjet[]) => {
          if (!demiFlux.contactProjet || !demiFlux.contactProjet.id) {
            this.contactprojets = resBody;
          } else {
            this.contactProjetService
              .find(demiFlux.contactProjet.id)
              .pipe(
                map((subRes: HttpResponse<IContactProjet>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IContactProjet[]) => (this.contactprojets = concatRes));
          }
        });

      this.contactTechniqueService
        .query({ filter: 'demiflux-is-null' })
        .pipe(
          map((res: HttpResponse<IContactTechnique[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IContactTechnique[]) => {
          if (!demiFlux.contactTechnique || !demiFlux.contactTechnique.id) {
            this.contacttechniques = resBody;
          } else {
            this.contactTechniqueService
              .find(demiFlux.contactTechnique.id)
              .pipe(
                map((subRes: HttpResponse<IContactTechnique>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IContactTechnique[]) => (this.contacttechniques = concatRes));
          }
        });

      this.pESITService
        .query({ filter: 'demiflux-is-null' })
        .pipe(
          map((res: HttpResponse<IPESIT[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPESIT[]) => {
          if (!demiFlux.pESIT || !demiFlux.pESIT.id) {
            this.pesits = resBody;
          } else {
            this.pESITService
              .find(demiFlux.pESIT.id)
              .pipe(
                map((subRes: HttpResponse<IPESIT>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPESIT[]) => (this.pesits = concatRes));
          }
        });

      this.sFTPService
        .query({ filter: 'demiflux-is-null' })
        .pipe(
          map((res: HttpResponse<ISFTP[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISFTP[]) => {
          if (!demiFlux.sFTP || !demiFlux.sFTP.id) {
            this.sftps = resBody;
          } else {
            this.sFTPService
              .find(demiFlux.sFTP.id)
              .pipe(
                map((subRes: HttpResponse<ISFTP>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISFTP[]) => (this.sftps = concatRes));
          }
        });

      this.demandeurService.query().subscribe((res: HttpResponse<IDemandeur[]>) => (this.demandeurs = res.body || []));

      this.fluxService.query().subscribe((res: HttpResponse<IFlux[]>) => (this.fluxes = res.body || []));
    });
  }

  updateForm(demiFlux: IDemiFlux): void {
    this.editForm.patchValue({
      id: demiFlux.id,
      appliname: demiFlux.appliname,
      partenairelocal: demiFlux.partenairelocal,
      partenairedistant: demiFlux.partenairedistant,
      directory: demiFlux.directory,
      filename: demiFlux.filename,
      mode: demiFlux.mode,
      typology: demiFlux.typology,
      type: demiFlux.type,
      hostname: demiFlux.hostname,
      port: demiFlux.port,
      contactProjet: demiFlux.contactProjet,
      contactTechnique: demiFlux.contactTechnique,
      pESIT: demiFlux.pESIT,
      sFTP: demiFlux.sFTP,
      demandeur: demiFlux.demandeur,
      flux: demiFlux.flux
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demiFlux = this.createFromForm();
    if (demiFlux.id !== undefined) {
      this.subscribeToSaveResponse(this.demiFluxService.update(demiFlux));
    } else {
      this.subscribeToSaveResponse(this.demiFluxService.create(demiFlux));
    }
  }

  private createFromForm(): IDemiFlux {
    return {
      ...new DemiFlux(),
      id: this.editForm.get(['id'])!.value,
      appliname: this.editForm.get(['appliname'])!.value,
      partenairelocal: this.editForm.get(['partenairelocal'])!.value,
      partenairedistant: this.editForm.get(['partenairedistant'])!.value,
      directory: this.editForm.get(['directory'])!.value,
      filename: this.editForm.get(['filename'])!.value,
      mode: this.editForm.get(['mode'])!.value,
      typology: this.editForm.get(['typology'])!.value,
      type: this.editForm.get(['type'])!.value,
      hostname: this.editForm.get(['hostname'])!.value,
      port: this.editForm.get(['port'])!.value,
      contactProjet: this.editForm.get(['contactProjet'])!.value,
      contactTechnique: this.editForm.get(['contactTechnique'])!.value,
      pESIT: this.editForm.get(['pESIT'])!.value,
      sFTP: this.editForm.get(['sFTP'])!.value,
      demandeur: this.editForm.get(['demandeur'])!.value,
      flux: this.editForm.get(['flux'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemiFlux>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
