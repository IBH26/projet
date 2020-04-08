import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { DemandeurUpdateComponent } from 'app/entities/demandeur/demandeur-update.component';
import { DemandeurService } from 'app/entities/demandeur/demandeur.service';
import { Demandeur } from 'app/shared/model/demandeur.model';

describe('Component Tests', () => {
  describe('Demandeur Management Update Component', () => {
    let comp: DemandeurUpdateComponent;
    let fixture: ComponentFixture<DemandeurUpdateComponent>;
    let service: DemandeurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [DemandeurUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DemandeurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DemandeurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DemandeurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Demandeur(123);
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
        const entity = new Demandeur();
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
