package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.DemiFlux;
import com.mycompany.myapp.service.DemiFluxService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.DemiFluxCriteria;
import com.mycompany.myapp.service.DemiFluxQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.DemiFlux}.
 */
@RestController
@RequestMapping("/api")
public class DemiFluxResource {

    private final Logger log = LoggerFactory.getLogger(DemiFluxResource.class);

    private static final String ENTITY_NAME = "demiFlux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemiFluxService demiFluxService;

    private final DemiFluxQueryService demiFluxQueryService;

    public DemiFluxResource(DemiFluxService demiFluxService, DemiFluxQueryService demiFluxQueryService) {
        this.demiFluxService = demiFluxService;
        this.demiFluxQueryService = demiFluxQueryService;
    }

    /**
     * {@code POST  /demi-fluxes} : Create a new demiFlux.
     *
     * @param demiFlux the demiFlux to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demiFlux, or with status {@code 400 (Bad Request)} if the demiFlux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demi-fluxes")
    public ResponseEntity<DemiFlux> createDemiFlux(@Valid @RequestBody DemiFlux demiFlux) throws URISyntaxException {
        log.debug("REST request to save DemiFlux : {}", demiFlux);
        if (demiFlux.getId() != null) {
            throw new BadRequestAlertException("A new demiFlux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemiFlux result = demiFluxService.save(demiFlux);
        return ResponseEntity.created(new URI("/api/demi-fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demi-fluxes} : Updates an existing demiFlux.
     *
     * @param demiFlux the demiFlux to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demiFlux,
     * or with status {@code 400 (Bad Request)} if the demiFlux is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demiFlux couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demi-fluxes")
    public ResponseEntity<DemiFlux> updateDemiFlux(@Valid @RequestBody DemiFlux demiFlux) throws URISyntaxException {
        log.debug("REST request to update DemiFlux : {}", demiFlux);
        if (demiFlux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemiFlux result = demiFluxService.save(demiFlux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demiFlux.getId().toString()))
            .body(result);
    }

     /**
     * {@code GET  /demi-fluxes} : get all the demiFluxes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demiFluxes in body.
     */
    @GetMapping("/demi-fluxes")
    public ResponseEntity<List<DemiFlux>> getAllDemiFluxes(DemiFluxCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DemiFluxes by criteria: {}", criteria);
        Page<DemiFlux> page = demiFluxQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /demi-fluxes/count} : count all the demiFluxes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/demi-fluxes/count")
    public ResponseEntity<Long> countDemiFluxes(DemiFluxCriteria criteria) {
        log.debug("REST request to count DemiFluxes by criteria: {}", criteria);
        return ResponseEntity.ok().body(demiFluxQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /demi-fluxes/:id} : get the "id" demiFlux.
     *
     * @param id the id of the demiFlux to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demiFlux, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demi-fluxes/{id}")
    public ResponseEntity<DemiFlux> getDemiFlux(@PathVariable Long id) {
        log.debug("REST request to get DemiFlux : {}", id);
        Optional<DemiFlux> demiFlux = demiFluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demiFlux);
    }

    /**
     * {@code DELETE  /demi-fluxes/:id} : delete the "id" demiFlux.
     *
     * @param id the id of the demiFlux to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demi-fluxes/{id}")
    public ResponseEntity<Void> deleteDemiFlux(@PathVariable Long id) {
        log.debug("REST request to delete DemiFlux : {}", id);
        demiFluxService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}


