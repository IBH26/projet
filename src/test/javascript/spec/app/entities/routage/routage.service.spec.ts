import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ROUTAGEService } from 'app/entities/routage/routage.service';
import { IROUTAGE, ROUTAGE } from 'app/shared/model/routage.model';

describe('Service Tests', () => {
  describe('ROUTAGE Service', () => {
    let injector: TestBed;
    let service: ROUTAGEService;
    let httpMock: HttpTestingController;
    let elemDefault: IROUTAGE;
    let expectedResult: IROUTAGE | IROUTAGE[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ROUTAGEService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ROUTAGE(0, 'AAAAAAA', false, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ROUTAGE', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ROUTAGE()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ROUTAGE', () => {
        const returnedFromService = Object.assign(
          {
            drName: 'BBBBBB',
            rename: true,
            maskFile: 'BBBBBB',
            directory: 'BBBBBB',
            handlingtype: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ROUTAGE', () => {
        const returnedFromService = Object.assign(
          {
            drName: 'BBBBBB',
            rename: true,
            maskFile: 'BBBBBB',
            directory: 'BBBBBB',
            handlingtype: 'BBBBBB'
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

      it('should delete a ROUTAGE', () => {
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
