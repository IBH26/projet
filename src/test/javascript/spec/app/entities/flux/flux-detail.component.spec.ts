import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { FluxDetailComponent } from 'app/entities/flux/flux-detail.component';
import { Flux } from 'app/shared/model/flux.model';

describe('Component Tests', () => {
  describe('Flux Management Detail Component', () => {
    let comp: FluxDetailComponent;
    let fixture: ComponentFixture<FluxDetailComponent>;
    const route = ({ data: of({ flux: new Flux(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [FluxDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FluxDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FluxDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load flux on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.flux).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
