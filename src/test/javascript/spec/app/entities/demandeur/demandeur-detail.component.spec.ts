import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { DemandeurDetailComponent } from 'app/entities/demandeur/demandeur-detail.component';
import { Demandeur } from 'app/shared/model/demandeur.model';

describe('Component Tests', () => {
  describe('Demandeur Management Detail Component', () => {
    let comp: DemandeurDetailComponent;
    let fixture: ComponentFixture<DemandeurDetailComponent>;
    const route = ({ data: of({ demandeur: new Demandeur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [DemandeurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DemandeurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DemandeurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load demandeur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.demandeur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
