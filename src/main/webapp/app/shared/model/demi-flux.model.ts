import { IContactProjet } from 'app/shared/model/contact-projet.model';
import { IContactTechnique } from 'app/shared/model/contact-technique.model';
import { IPESIT } from 'app/shared/model/pesit.model';
import { ISFTP } from 'app/shared/model/sftp.model';
import { IROUTAGE } from 'app/shared/model/routage.model';
import { IDemandeur } from 'app/shared/model/demandeur.model';
import { IFlux } from 'app/shared/model/flux.model';
import { FHU } from 'app/shared/model/enumerations/fhu.model';
import { Mode } from 'app/shared/model/enumerations/mode.model';
import { Type } from 'app/shared/model/enumerations/type.model';

export interface IDemiFlux {
  id?: number;
  appliname?: string;
  partenairelocal?: FHU;
  partenairedistant?: string;
  directory?: string;
  filename?: string;
  mode?: Mode;
  typology?: Type;
  type?: string;
  hostname?: string;
  port?: number;
  contactProjet?: IContactProjet;
  contactTechnique?: IContactTechnique;
  pESIT?: IPESIT;
  sFTP?: ISFTP;
  routages?: IROUTAGE[];
  demandeur?: IDemandeur;
  flux?: IFlux;
}

export class DemiFlux implements IDemiFlux {
  constructor(
    public id?: number,
    public appliname?: string,
    public partenairelocal?: FHU,
    public partenairedistant?: string,
    public directory?: string,
    public filename?: string,
    public mode?: Mode,
    public typology?: Type,
    public type?: string,
    public hostname?: string,
    public port?: number,
    public contactProjet?: IContactProjet,
    public contactTechnique?: IContactTechnique,
    public pESIT?: IPESIT,
    public sFTP?: ISFTP,
    public routages?: IROUTAGE[],
    public demandeur?: IDemandeur,
    public flux?: IFlux
  ) {}
}
