import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PESITDetailComponent } from 'app/entities/pesit/pesit-detail.component';
import { PESIT } from 'app/shared/model/pesit.model';

describe('Component Tests', () => {
  describe('PESIT Management Detail Component', () => {
    let comp: PESITDetailComponent;
    let fixture: ComponentFixture<PESITDetailComponent>;
    const route = ({ data: of({ pESIT: new PESIT(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PESITDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PESITDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PESITDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pESIT on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pESIT).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
