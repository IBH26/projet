import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams , HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFlux } from 'app/shared/model/flux.model';
// import { map } from 'rxjs/internal/operators/map';
// import { IDemiFlux } from 'app/shared/model/demi-flux.model';

type EntityResponseType = HttpResponse<IFlux>;
type EntityArrayResponseType = HttpResponse<IFlux[]>;

@Injectable({ providedIn: 'root' })
export class FluxService {
  public resourceUrl = SERVER_API_URL + 'api/fluxes';
  public url2 ="download" ;
  public u= SERVER_API_URL + 'api/demi-fluxes ';
  constructor(protected http: HttpClient) {}

  create(flux: IFlux): Observable<EntityResponseType> {
    return this.http.post<IFlux>(this.resourceUrl, flux, { observe: 'response' });
  }

  update(flux: IFlux): Observable<EntityResponseType> {
    return this.http.put<IFlux>(this.resourceUrl, flux, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFlux>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFlux[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAlbums() : Observable<any>
  {
    return this.http.get(`${this.resourceUrl}`) ;
  }

  getdemi(fluxId : number) : Observable<any>
  {
    return this.http.get(`${this.resourceUrl}/${fluxId}`) ;
    
  }

  getkk(fluxId : number) : Observable<any>
  {
    return this.http.get(`${this.u}/${fluxId}`) ;
    
  }
 
  
  

}
