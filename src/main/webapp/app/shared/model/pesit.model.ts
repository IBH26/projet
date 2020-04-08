export interface IPESIT {
  id?: number;
  codeSite?: string;
  codeApplication?: string;
  transcoding?: string;
  compression?: string;
}

export class PESIT implements IPESIT {
  constructor(
    public id?: number,
    public codeSite?: string,
    public codeApplication?: string,
    public transcoding?: string,
    public compression?: string
  ) {}
}
