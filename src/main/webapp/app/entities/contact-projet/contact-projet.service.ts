import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContactProjet } from 'app/shared/model/contact-projet.model';

type EntityResponseType = HttpResponse<IContactProjet>;
type EntityArrayResponseType = HttpResponse<IContactProjet[]>;

@Injectable({ providedIn: 'root' })
export class ContactProjetService {
  public resourceUrl = SERVER_API_URL + 'api/contact-projets';

  constructor(protected http: HttpClient) {}

  create(contactProjet: IContactProjet): Observable<EntityResponseType> {
    return this.http.post<IContactProjet>(this.resourceUrl, contactProjet, { observe: 'response' });
  }

  update(contactProjet: IContactProjet): Observable<EntityResponseType> {
    return this.http.put<IContactProjet>(this.resourceUrl, contactProjet, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IContactProjet>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContactProjet[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
