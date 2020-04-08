import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PESITUpdateComponent } from 'app/entities/pesit/pesit-update.component';
import { PESITService } from 'app/entities/pesit/pesit.service';
import { PESIT } from 'app/shared/model/pesit.model';

describe('Component Tests', () => {
  describe('PESIT Management Update Component', () => {
    let comp: PESITUpdateComponent;
    let fixture: ComponentFixture<PESITUpdateComponent>;
    let service: PESITService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PESITUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PESITUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PESITUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PESITService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PESIT(123);
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
        const entity = new PESIT();
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
