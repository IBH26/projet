import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ROUTAGEComponent } from 'app/entities/routage/routage.component';
import { ROUTAGEService } from 'app/entities/routage/routage.service';
import { ROUTAGE } from 'app/shared/model/routage.model';

describe('Component Tests', () => {
  describe('ROUTAGE Management Component', () => {
    let comp: ROUTAGEComponent;
    let fixture: ComponentFixture<ROUTAGEComponent>;
    let service: ROUTAGEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ROUTAGEComponent]
      })
        .overrideTemplate(ROUTAGEComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ROUTAGEComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ROUTAGEService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ROUTAGE(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.rOUTAGES && comp.rOUTAGES[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
