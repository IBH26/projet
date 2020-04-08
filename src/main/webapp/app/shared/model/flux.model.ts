import { IDemiFlux } from 'app/shared/model/demi-flux.model';
import { Env } from 'app/shared/model/enumerations/env.model';

export interface IFlux {
  id?: number;
  codeMega?: string;
  envir?: Env;
  demiFluxes?: IDemiFlux[];
}

export class Flux implements IFlux {
  constructor(public id?: number, public codeMega?: string, public envir?: Env, public demiFluxes?: IDemiFlux[]) {}
}
