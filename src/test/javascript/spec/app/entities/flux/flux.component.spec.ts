import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { FluxComponent } from 'app/entities/flux/flux.component';
import { FluxService } from 'app/entities/flux/flux.service';
import { Flux } from 'app/shared/model/flux.model';

describe('Component Tests', () => {
  describe('Flux Management Component', () => {
    let comp: FluxComponent;
    let fixture: ComponentFixture<FluxComponent>;
    let service: FluxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FluxComponent]
      })
        .overrideTemplate(FluxComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FluxComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FluxService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Flux(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fluxes && comp.fluxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
