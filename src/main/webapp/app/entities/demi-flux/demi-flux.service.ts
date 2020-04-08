import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse ,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDemiFlux } from 'app/shared/model/demi-flux.model';

type EntityResponseType = HttpResponse<IDemiFlux>;
type EntityArrayResponseType = HttpResponse<IDemiFlux[]>;

@Injectable({ providedIn: 'root' })
export class DemiFluxService {
  public resourceUrl = SERVER_API_URL + 'api/demi-fluxes';
  // public url2 ="download"
  constructor(protected http: HttpClient) {}

  create(demiFlux: IDemiFlux): Observable<EntityResponseType> {
    return this.http.post<IDemiFlux>(this.resourceUrl, demiFlux, { observe: 'response' });
  }

  update(demiFlux: IDemiFlux): Observable<EntityResponseType> {
    return this.http.put<IDemiFlux>(this.resourceUrl, demiFlux, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDemiFlux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDemiFlux[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  
}
