export interface IContactTechnique {
  id?: number;
  nameCT?: string;
  functionCT?: string;
  phoneNumberCT?: string;
  emailCT?: string;
}

export class ContactTechnique implements IContactTechnique {
  constructor(
    public id?: number,
    public nameCT?: string,
    public functionCT?: string,
    public phoneNumberCT?: string,
    public emailCT?: string
  ) {}
}
