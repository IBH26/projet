export interface IContactProjet {
  id?: number;
  nameCP?: string;
  functionCP?: string;
  phoneNumberCP?: string;
  emailCP?: string;
}

export class ContactProjet implements IContactProjet {
  constructor(
    public id?: number,
    public nameCP?: string,
    public functionCP?: string,
    public phoneNumberCP?: string,
    public emailCP?: string
  ) {}
}
