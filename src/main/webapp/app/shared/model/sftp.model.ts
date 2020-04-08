export interface ISFTP {
  id?: number;
  formatTransfer?: string;
  user?: string;
  key?: string;
}

export class SFTP implements ISFTP {
  constructor(public id?: number, public formatTransfer?: string, public user?: string, public key?: string) {}
}
