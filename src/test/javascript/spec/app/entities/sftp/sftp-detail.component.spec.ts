import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { SFTPDetailComponent } from 'app/entities/sftp/sftp-detail.component';
import { SFTP } from 'app/shared/model/sftp.model';

describe('Component Tests', () => {
  describe('SFTP Management Detail Component', () => {
    let comp: SFTPDetailComponent;
    let fixture: ComponentFixture<SFTPDetailComponent>;
    const route = ({ data: of({ sFTP: new SFTP(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [SFTPDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SFTPDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SFTPDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sFTP on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sFTP).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
