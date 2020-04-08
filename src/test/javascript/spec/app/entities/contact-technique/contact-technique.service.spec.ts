import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ContactTechniqueService } from 'app/entities/contact-technique/contact-technique.service';
import { IContactTechnique, ContactTechnique } from 'app/shared/model/contact-technique.model';

describe('Service Tests', () => {
  describe('ContactTechnique Service', () => {
    let injector: TestBed;
    let service: ContactTechniqueService;
    let httpMock: HttpTestingController;
    let elemDefault: IContactTechnique;
    let expectedResult: IContactTechnique | IContactTechnique[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ContactTechniqueService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ContactTechnique(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ContactTechnique', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ContactTechnique()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ContactTechnique', () => {
        const returnedFromService = Object.assign(
          {
            nameCT: 'BBBBBB',
            functionCT: 'BBBBBB',
            phoneNumberCT: 'BBBBBB',
            emailCT: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ContactTechnique', () => {
        const returnedFromService = Object.assign(
          {
            nameCT: 'BBBBBB',
            functionCT: 'BBBBBB',
            phoneNumberCT: 'BBBBBB',
            emailCT: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ContactTechnique', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
