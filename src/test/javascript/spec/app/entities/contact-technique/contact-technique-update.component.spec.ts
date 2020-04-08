import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ContactTechniqueUpdateComponent } from 'app/entities/contact-technique/contact-technique-update.component';
import { ContactTechniqueService } from 'app/entities/contact-technique/contact-technique.service';
import { ContactTechnique } from 'app/shared/model/contact-technique.model';

describe('Component Tests', () => {
  describe('ContactTechnique Management Update Component', () => {
    let comp: ContactTechniqueUpdateComponent;
    let fixture: ComponentFixture<ContactTechniqueUpdateComponent>;
    let service: ContactTechniqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactTechniqueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ContactTechniqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactTechniqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactTechniqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContactTechnique(123);
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
        const entity = new ContactTechnique();
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
