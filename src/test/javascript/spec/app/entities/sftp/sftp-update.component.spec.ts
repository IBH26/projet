import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { SFTPUpdateComponent } from 'app/entities/sftp/sftp-update.component';
import { SFTPService } from 'app/entities/sftp/sftp.service';
import { SFTP } from 'app/shared/model/sftp.model';

describe('Component Tests', () => {
  describe('SFTP Management Update Component', () => {
    let comp: SFTPUpdateComponent;
    let fixture: ComponentFixture<SFTPUpdateComponent>;
    let service: SFTPService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [SFTPUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SFTPUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SFTPUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SFTPService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SFTP(123);
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
        const entity = new SFTP();
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
