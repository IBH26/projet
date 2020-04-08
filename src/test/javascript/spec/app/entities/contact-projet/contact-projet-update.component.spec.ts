import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ContactProjetUpdateComponent } from 'app/entities/contact-projet/contact-projet-update.component';
import { ContactProjetService } from 'app/entities/contact-projet/contact-projet.service';
import { ContactProjet } from 'app/shared/model/contact-projet.model';

describe('Component Tests', () => {
  describe('ContactProjet Management Update Component', () => {
    let comp: ContactProjetUpdateComponent;
    let fixture: ComponentFixture<ContactProjetUpdateComponent>;
    let service: ContactProjetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactProjetUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ContactProjetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactProjetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactProjetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContactProjet(123);
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
        const entity = new ContactProjet();
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
