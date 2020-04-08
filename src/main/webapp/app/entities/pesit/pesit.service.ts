import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPESIT } from 'app/shared/model/pesit.model';

type EntityResponseType = HttpResponse<IPESIT>;
type EntityArrayResponseType = HttpResponse<IPESIT[]>;

@Injectable({ providedIn: 'root' })
export class PESITService {
  public resourceUrl = SERVER_API_URL + 'api/pesits';

  constructor(protected http: HttpClient) {}

  create(pESIT: IPESIT): Observable<EntityResponseType> {
    return this.http.post<IPESIT>(this.resourceUrl, pESIT, { observe: 'response' });
  }

  update(pESIT: IPESIT): Observable<EntityResponseType> {
    return this.http.put<IPESIT>(this.resourceUrl, pESIT, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPESIT>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPESIT[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
