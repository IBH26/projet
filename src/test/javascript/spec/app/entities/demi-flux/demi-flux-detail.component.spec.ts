import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { DemiFluxDetailComponent } from 'app/entities/demi-flux/demi-flux-detail.component';
import { DemiFlux } from 'app/shared/model/demi-flux.model';

describe('Component Tests', () => {
  describe('DemiFlux Management Detail Component', () => {
    let comp: DemiFluxDetailComponent;
    let fixture: ComponentFixture<DemiFluxDetailComponent>;
    const route = ({ data: of({ demiFlux: new DemiFlux(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [DemiFluxDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DemiFluxDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DemiFluxDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load demiFlux on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.demiFlux).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
