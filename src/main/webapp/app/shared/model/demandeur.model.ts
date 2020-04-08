export interface IDemandeur {
  id?: number;
  nameD?: string;
  functionD?: string;
  projectD?: string;
}

export class Demandeur implements IDemandeur {
  constructor(public id?: number, public nameD?: string, public functionD?: string, public projectD?: string) {}
}
