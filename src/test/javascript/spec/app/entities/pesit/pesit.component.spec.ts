import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { PESITComponent } from 'app/entities/pesit/pesit.component';
import { PESITService } from 'app/entities/pesit/pesit.service';
import { PESIT } from 'app/shared/model/pesit.model';

describe('Component Tests', () => {
  describe('PESIT Management Component', () => {
    let comp: PESITComponent;
    let fixture: ComponentFixture<PESITComponent>;
    let service: PESITService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PESITComponent]
      })
        .overrideTemplate(PESITComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PESITComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PESITService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PESIT(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pESITS && comp.pESITS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
