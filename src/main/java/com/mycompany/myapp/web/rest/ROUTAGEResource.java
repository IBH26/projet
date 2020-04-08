package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ROUTAGE;
import com.mycompany.myapp.service.ROUTAGEService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.ROUTAGE}.
 */
@RestController
@RequestMapping("/api")
public class ROUTAGEResource {

    private final Logger log = LoggerFactory.getLogger(ROUTAGEResource.class);

    private static final String ENTITY_NAME = "rOUTAGE";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ROUTAGEService rOUTAGEService;

    public ROUTAGEResource(ROUTAGEService rOUTAGEService) {
        this.rOUTAGEService = rOUTAGEService;
    }

    /**
     * {@code POST  /routages} : Create a new rOUTAGE.
     *
     * @param rOUTAGE the rOUTAGE to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rOUTAGE, or with status {@code 400 (Bad Request)} if the rOUTAGE has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/routages")
    public ResponseEntity<ROUTAGE> createROUTAGE(@Valid @RequestBody ROUTAGE rOUTAGE) throws URISyntaxException {
        log.debug("REST request to save ROUTAGE : {}", rOUTAGE);
        if (rOUTAGE.getId() != null) {
            throw new BadRequestAlertException("A new rOUTAGE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ROUTAGE result = rOUTAGEService.save(rOUTAGE);
        return ResponseEntity.created(new URI("/api/routages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /routages} : Updates an existing rOUTAGE.
     *
     * @param rOUTAGE the rOUTAGE to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rOUTAGE,
     * or with status {@code 400 (Bad Request)} if the rOUTAGE is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rOUTAGE couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/routages")
    public ResponseEntity<ROUTAGE> updateROUTAGE(@Valid @RequestBody ROUTAGE rOUTAGE) throws URISyntaxException {
        log.debug("REST request to update ROUTAGE : {}", rOUTAGE);
        if (rOUTAGE.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ROUTAGE result = rOUTAGEService.save(rOUTAGE);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rOUTAGE.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /routages} : get all the rOUTAGES.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rOUTAGES in body.
     */
    @GetMapping("/routages")
    public List<ROUTAGE> getAllROUTAGES() {
        log.debug("REST request to get all ROUTAGES");
        return rOUTAGEService.findAll();
    }

    /**
     * {@code GET  /routages/:id} : get the "id" rOUTAGE.
     *
     * @param id the id of the rOUTAGE to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rOUTAGE, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/routages/{id}")
    public ResponseEntity<ROUTAGE> getROUTAGE(@PathVariable Long id) {
        log.debug("REST request to get ROUTAGE : {}", id);
        Optional<ROUTAGE> rOUTAGE = rOUTAGEService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rOUTAGE);
    }

    /**
     * {@code DELETE  /routages/:id} : delete the "id" rOUTAGE.
     *
     * @param id the id of the rOUTAGE to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/routages/{id}")
    public ResponseEntity<Void> deleteROUTAGE(@PathVariable Long id) {
        log.debug("REST request to delete ROUTAGE : {}", id);
        rOUTAGEService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
