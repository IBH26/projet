import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IROUTAGE } from 'app/shared/model/routage.model';

type EntityResponseType = HttpResponse<IROUTAGE>;
type EntityArrayResponseType = HttpResponse<IROUTAGE[]>;

@Injectable({ providedIn: 'root' })
export class ROUTAGEService {
  public resourceUrl = SERVER_API_URL + 'api/routages';

  constructor(protected http: HttpClient) {}

  create(rOUTAGE: IROUTAGE): Observable<EntityResponseType> {
    return this.http.post<IROUTAGE>(this.resourceUrl, rOUTAGE, { observe: 'response' });
  }

  update(rOUTAGE: IROUTAGE): Observable<EntityResponseType> {
    return this.http.put<IROUTAGE>(this.resourceUrl, rOUTAGE, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IROUTAGE>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IROUTAGE[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
