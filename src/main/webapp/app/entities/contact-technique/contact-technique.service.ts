import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContactTechnique } from 'app/shared/model/contact-technique.model';

type EntityResponseType = HttpResponse<IContactTechnique>;
type EntityArrayResponseType = HttpResponse<IContactTechnique[]>;

@Injectable({ providedIn: 'root' })
export class ContactTechniqueService {
  public resourceUrl = SERVER_API_URL + 'api/contact-techniques';

  constructor(protected http: HttpClient) {}

  create(contactTechnique: IContactTechnique): Observable<EntityResponseType> {
    return this.http.post<IContactTechnique>(this.resourceUrl, contactTechnique, { observe: 'response' });
  }

  update(contactTechnique: IContactTechnique): Observable<EntityResponseType> {
    return this.http.put<IContactTechnique>(this.resourceUrl, contactTechnique, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IContactTechnique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IContactTechnique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
