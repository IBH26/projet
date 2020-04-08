package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Flux;
import com.mycompany.myapp.service.FluxService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.FluxCriteria;
import com.mycompany.myapp.service.FluxQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Flux}.
 */
@RestController
@RequestMapping("/api")
public class FluxResource {

    private final Logger log = LoggerFactory.getLogger(FluxResource.class);

    private static final String ENTITY_NAME = "flux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FluxService fluxService;

    private final FluxQueryService fluxQueryService;

    public FluxResource(FluxService fluxService, FluxQueryService fluxQueryService) {
        this.fluxService = fluxService;
        this.fluxQueryService = fluxQueryService;
    }

    /**
     * {@code POST  /fluxes} : Create a new flux.
     *
     * @param flux the flux to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flux, or with status {@code 400 (Bad Request)} if the flux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fluxes")
    public ResponseEntity<Flux> createFlux(@Valid @RequestBody Flux flux) throws URISyntaxException {
        log.debug("REST request to save Flux : {}", flux);
        if (flux.getId() != null) {
            throw new BadRequestAlertException("A new flux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Flux result = fluxService.save(flux);
        return ResponseEntity.created(new URI("/api/fluxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fluxes} : Updates an existing flux.
     *
     * @param flux the flux to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flux,
     * or with status {@code 400 (Bad Request)} if the flux is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flux couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fluxes")
    public ResponseEntity<Flux> updateFlux(@Valid @RequestBody Flux flux) throws URISyntaxException {
        log.debug("REST request to update Flux : {}", flux);
        if (flux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Flux result = fluxService.save(flux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, flux.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fluxes} : get all the fluxes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fluxes in body.
     */
    @GetMapping("/fluxes")
    public ResponseEntity<List<Flux>> getAllFluxes(FluxCriteria criteria) {
        log.debug("REST request to get Fluxes by criteria: {}", criteria);
        List<Flux> entityList = fluxQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /fluxes/count} : count all the fluxes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/fluxes/count")
    public ResponseEntity<Long> countFluxes(FluxCriteria criteria) {
        log.debug("REST request to count Fluxes by criteria: {}", criteria);
        return ResponseEntity.ok().body(fluxQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /fluxes/:id} : get the "id" flux.
     *
     * @param id the id of the flux to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flux, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fluxes/{id}")
    public ResponseEntity<Flux> getFlux(@PathVariable Long id) {
        log.debug("REST request to get Flux : {}", id);
        Optional<Flux> flux = fluxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flux);
    }

    /**
     * {@code DELETE  /fluxes/:id} : delete the "id" flux.
     *
     * @param id the id of the flux to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fluxes/{id}")
    public ResponseEntity<Void> deleteFlux(@PathVariable Long id) {
        log.debug("REST request to delete Flux : {}", id);
        fluxService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
