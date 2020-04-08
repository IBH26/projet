import { Component, OnInit } from '@angular/core';
import { FluxService } from '../flux.service';
import { IFlux } from 'app/shared/model/flux.model';

@Component({
  selector: 'jhi-search-flux',
  templateUrl: './search-flux.component.html',
  styleUrls: ['./search-flux.component.scss']
})
export class SearchFluxComponent  {
  FluxSelected? : Number  ;
  fluxes?: IFlux[];
  flu? : any[]; ;
  

constructor(protected fluxService: FluxService) { }
ngOnInit() {
    this.fluxService.getAlbums()
    .subscribe (
   data=>
      {this.fluxes = data  ;}
        //this.fluxes? = Array.of(this.fluxes?);
      //console.log(data) }
    )}

onFluxSelected(val:any)
  { 
    //this.custofu(val) ;
    //console.log(val)

    this.fluxService.getdemi(val)
    .subscribe(
    data=>{this.flu = data
      this.flu = Array.of(this.flu) ;
          console.log(this.flu) ; },

          error => { console.log(error);}
            ) ;
}


oniDemiFluxSelected(val:any)
{ 
  //this.custofu(val) ;
  //console.log(val)

  this.fluxService.getkk(val)
  .subscribe(
  data=>{this.flu = data
    this.flu = Array.of(this.flu) ;
        console.log(this.flu) ; },

        error => { console.log(error);}
          ) ;
}     



  
}
