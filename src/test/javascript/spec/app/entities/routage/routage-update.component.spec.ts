import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ROUTAGEUpdateComponent } from 'app/entities/routage/routage-update.component';
import { ROUTAGEService } from 'app/entities/routage/routage.service';
import { ROUTAGE } from 'app/shared/model/routage.model';

describe('Component Tests', () => {
  describe('ROUTAGE Management Update Component', () => {
    let comp: ROUTAGEUpdateComponent;
    let fixture: ComponentFixture<ROUTAGEUpdateComponent>;
    let service: ROUTAGEService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ROUTAGEUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ROUTAGEUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ROUTAGEUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ROUTAGEService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ROUTAGE(123);
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
        const entity = new ROUTAGE();
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
