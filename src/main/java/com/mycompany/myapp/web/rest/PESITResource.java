package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PESIT;
import com.mycompany.myapp.service.PESITService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PESIT}.
 */
@RestController
@RequestMapping("/api")
public class PESITResource {

    private final Logger log = LoggerFactory.getLogger(PESITResource.class);

    private static final String ENTITY_NAME = "pESIT";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PESITService pESITService;

    public PESITResource(PESITService pESITService) {
        this.pESITService = pESITService;
    }

    /**
     * {@code POST  /pesits} : Create a new pESIT.
     *
     * @param pESIT the pESIT to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pESIT, or with status {@code 400 (Bad Request)} if the pESIT has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pesits")
    public ResponseEntity<PESIT> createPESIT(@RequestBody PESIT pESIT) throws URISyntaxException {
        log.debug("REST request to save PESIT : {}", pESIT);
        if (pESIT.getId() != null) {
            throw new BadRequestAlertException("A new pESIT cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PESIT result = pESITService.save(pESIT);
        return ResponseEntity.created(new URI("/api/pesits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pesits} : Updates an existing pESIT.
     *
     * @param pESIT the pESIT to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pESIT,
     * or with status {@code 400 (Bad Request)} if the pESIT is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pESIT couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pesits")
    public ResponseEntity<PESIT> updatePESIT(@RequestBody PESIT pESIT) throws URISyntaxException {
        log.debug("REST request to update PESIT : {}", pESIT);
        if (pESIT.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PESIT result = pESITService.save(pESIT);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pESIT.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pesits} : get all the pESITS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pESITS in body.
     */
    @GetMapping("/pesits")
    public List<PESIT> getAllPESITS() {
        log.debug("REST request to get all PESITS");
        return pESITService.findAll();
    }

    /**
     * {@code GET  /pesits/:id} : get the "id" pESIT.
     *
     * @param id the id of the pESIT to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pESIT, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pesits/{id}")
    public ResponseEntity<PESIT> getPESIT(@PathVariable Long id) {
        log.debug("REST request to get PESIT : {}", id);
        Optional<PESIT> pESIT = pESITService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pESIT);
    }

    /**
     * {@code DELETE  /pesits/:id} : delete the "id" pESIT.
     *
     * @param id the id of the pESIT to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pesits/{id}")
    public ResponseEntity<Void> deletePESIT(@PathVariable Long id) {
        log.debug("REST request to delete PESIT : {}", id);
        pESITService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
