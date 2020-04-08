import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ContactTechniqueDetailComponent } from 'app/entities/contact-technique/contact-technique-detail.component';
import { ContactTechnique } from 'app/shared/model/contact-technique.model';

describe('Component Tests', () => {
  describe('ContactTechnique Management Detail Component', () => {
    let comp: ContactTechniqueDetailComponent;
    let fixture: ComponentFixture<ContactTechniqueDetailComponent>;
    const route = ({ data: of({ contactTechnique: new ContactTechnique(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactTechniqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ContactTechniqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContactTechniqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load contactTechnique on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contactTechnique).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
