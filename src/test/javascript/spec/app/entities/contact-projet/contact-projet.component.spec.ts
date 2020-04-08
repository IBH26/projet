import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ContactProjetComponent } from 'app/entities/contact-projet/contact-projet.component';
import { ContactProjetService } from 'app/entities/contact-projet/contact-projet.service';
import { ContactProjet } from 'app/shared/model/contact-projet.model';

describe('Component Tests', () => {
  describe('ContactProjet Management Component', () => {
    let comp: ContactProjetComponent;
    let fixture: ComponentFixture<ContactProjetComponent>;
    let service: ContactProjetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactProjetComponent]
      })
        .overrideTemplate(ContactProjetComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactProjetComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactProjetService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContactProjet(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contactProjets && comp.contactProjets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
