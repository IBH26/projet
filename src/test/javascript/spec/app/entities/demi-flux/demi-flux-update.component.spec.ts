import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { DemiFluxUpdateComponent } from 'app/entities/demi-flux/demi-flux-update.component';
import { DemiFluxService } from 'app/entities/demi-flux/demi-flux.service';
import { DemiFlux } from 'app/shared/model/demi-flux.model';

describe('Component Tests', () => {
  describe('DemiFlux Management Update Component', () => {
    let comp: DemiFluxUpdateComponent;
    let fixture: ComponentFixture<DemiFluxUpdateComponent>;
    let service: DemiFluxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [DemiFluxUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DemiFluxUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemiFluxUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemiFluxService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DemiFlux(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DemiFlux();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
