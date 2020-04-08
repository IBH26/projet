import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { DemandeurComponent } from 'app/entities/demandeur/demandeur.component';
import { DemandeurService } from 'app/entities/demandeur/demandeur.service';
import { Demandeur } from 'app/shared/model/demandeur.model';

describe('Component Tests', () => {
  describe('Demandeur Management Component', () => {
    let comp: DemandeurComponent;
    let fixture: ComponentFixture<DemandeurComponent>;
    let service: DemandeurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [DemandeurComponent]
      })
        .overrideTemplate(DemandeurComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemandeurComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemandeurService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Demandeur(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.demandeurs && comp.demandeurs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
