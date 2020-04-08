import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemandeur } from 'app/shared/model/demandeur.model';

type EntityResponseType = HttpResponse<IDemandeur>;
type EntityArrayResponseType = HttpResponse<IDemandeur[]>;

@Injectable({ providedIn: 'root' })
export class DemandeurService {
  public resourceUrl = SERVER_API_URL + 'api/demandeurs';

  constructor(protected http: HttpClient) {}

  create(demandeur: IDemandeur): Observable<EntityResponseType> {
    return this.http.post<IDemandeur>(this.resourceUrl, demandeur, { observe: 'response' });
  }

  update(demandeur: IDemandeur): Observable<EntityResponseType> {
    return this.http.put<IDemandeur>(this.resourceUrl, demandeur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDemandeur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDemandeur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
