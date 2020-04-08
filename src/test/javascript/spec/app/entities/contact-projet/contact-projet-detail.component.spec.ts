import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ContactProjetDetailComponent } from 'app/entities/contact-projet/contact-projet-detail.component';
import { ContactProjet } from 'app/shared/model/contact-projet.model';

describe('Component Tests', () => {
  describe('ContactProjet Management Detail Component', () => {
    let comp: ContactProjetDetailComponent;
    let fixture: ComponentFixture<ContactProjetDetailComponent>;
    const route = ({ data: of({ contactProjet: new ContactProjet(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ContactProjetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ContactProjetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContactProjetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load contactProjet on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contactProjet).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
