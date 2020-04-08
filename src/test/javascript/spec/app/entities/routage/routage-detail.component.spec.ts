import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ROUTAGEDetailComponent } from 'app/entities/routage/routage-detail.component';
import { ROUTAGE } from 'app/shared/model/routage.model';

describe('Component Tests', () => {
  describe('ROUTAGE Management Detail Component', () => {
    let comp: ROUTAGEDetailComponent;
    let fixture: ComponentFixture<ROUTAGEDetailComponent>;
    const route = ({ data: of({ rOUTAGE: new ROUTAGE(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ROUTAGEDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ROUTAGEDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ROUTAGEDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rOUTAGE on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rOUTAGE).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
