import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { SFTPComponent } from 'app/entities/sftp/sftp.component';
import { SFTPService } from 'app/entities/sftp/sftp.service';
import { SFTP } from 'app/shared/model/sftp.model';

describe('Component Tests', () => {
  describe('SFTP Management Component', () => {
    let comp: SFTPComponent;
    let fixture: ComponentFixture<SFTPComponent>;
    let service: SFTPService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [SFTPComponent]
      })
        .overrideTemplate(SFTPComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SFTPComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SFTPService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SFTP(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sFTPS && comp.sFTPS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
