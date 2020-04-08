import { IDemiFlux } from 'app/shared/model/demi-flux.model';

export interface IROUTAGE {
  id?: number;
  drName?: string;
  rename?: boolean;
  maskFile?: string;
  directory?: string;
  handlingtype?: string;
  demiflux?: IDemiFlux;
}

export class ROUTAGE implements IROUTAGE {
  constructor(
    public id?: number,
    public drName?: string,
    public rename?: boolean,
    public maskFile?: string,
    public directory?: string,
    public handlingtype?: string,
    public demiflux?: IDemiFlux
  ) {
    this.rename = this.rename || false;
  }
}
