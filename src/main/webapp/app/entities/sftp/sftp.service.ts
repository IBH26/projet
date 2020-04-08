import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISFTP } from 'app/shared/model/sftp.model';

type EntityResponseType = HttpResponse<ISFTP>;
type EntityArrayResponseType = HttpResponse<ISFTP[]>;

@Injectable({ providedIn: 'root' })
export class SFTPService {
  public resourceUrl = SERVER_API_URL + 'api/sftps';

  constructor(protected http: HttpClient) {}

  create(sFTP: ISFTP): Observable<EntityResponseType> {
    return this.http.post<ISFTP>(this.resourceUrl, sFTP, { observe: 'response' });
  }

  update(sFTP: ISFTP): Observable<EntityResponseType> {
    return this.http.put<ISFTP>(this.resourceUrl, sFTP, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISFTP>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISFTP[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
