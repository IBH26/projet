import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { ContactTechniqueComponent } from 'app/entities/contact-technique/contact-technique.component';
import { ContactTechniqueService } from 'app/entities/contact-technique/contact-technique.service';
import { ContactTechnique } from 'app/shared/model/contact-technique.model';

describe('Component Tests', () => {
  describe('ContactTechnique Management Component', () => {
    let comp: ContactTechniqueComponent;
    let fixture: ComponentFixture<ContactTechniqueComponent>;
    let service: ContactTechniqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactTechniqueComponent]
      })
        .overrideTemplate(ContactTechniqueComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContactTechniqueComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContactTechniqueService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContactTechnique(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contactTechniques && comp.contactTechniques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
