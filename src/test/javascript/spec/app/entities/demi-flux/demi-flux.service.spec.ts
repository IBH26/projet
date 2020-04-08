import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DemiFluxService } from 'app/entities/demi-flux/demi-flux.service';
import { IDemiFlux, DemiFlux } from 'app/shared/model/demi-flux.model';
import { FHU } from 'app/shared/model/enumerations/fhu.model';
import { Mode } from 'app/shared/model/enumerations/mode.model';
import { Type } from 'app/shared/model/enumerations/type.model';

describe('Service Tests', () => {
  describe('DemiFlux Service', () => {
    let injector: TestBed;
    let service: DemiFluxService;
    let httpMock: HttpTestingController;
    let elemDefault: IDemiFlux;
    let expectedResult: IDemiFlux | IDemiFlux[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DemiFluxService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new DemiFlux(0, 'AAAAAAA', FHU.FHU1, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', Mode.PUSH, Type.IN, 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DemiFlux', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new DemiFlux()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DemiFlux', () => {
        const returnedFromService = Object.assign(
          {
            appliname: 'BBBBBB',
            partenairelocal: 'BBBBBB',
            partenairedistant: 'BBBBBB',
            directory: 'BBBBBB',
            filename: 'BBBBBB',
            mode: 'BBBBBB',
            typology: 'BBBBBB',
            type: 'BBBBBB',
            hostname: 'BBBBBB',
            port: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DemiFlux', () => {
        const returnedFromService = Object.assign(
          {
            appliname: 'BBBBBB',
            partenairelocal: 'BBBBBB',
            partenairedistant: 'BBBBBB',
            directory: 'BBBBBB',
            filename: 'BBBBBB',
            mode: 'BBBBBB',
            typology: 'BBBBBB',
            type: 'BBBBBB',
            hostname: 'BBBBBB',
            port: 1
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

      it('should delete a DemiFlux', () => {
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
