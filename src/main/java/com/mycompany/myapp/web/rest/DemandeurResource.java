package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Demandeur;
import com.mycompany.myapp.service.DemandeurService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Demandeur}.
 */
@RestController
@RequestMapping("/api")
public class DemandeurResource {

    private final Logger log = LoggerFactory.getLogger(DemandeurResource.class);

    private static final String ENTITY_NAME = "demandeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeurService demandeurService;

    public DemandeurResource(DemandeurService demandeurService) {
        this.demandeurService = demandeurService;
    }

    /**
     * {@code POST  /demandeurs} : Create a new demandeur.
     *
     * @param demandeur the demandeur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demandeur, or with status {@code 400 (Bad Request)} if the demandeur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demandeurs")
    public ResponseEntity<Demandeur> createDemandeur(@Valid @RequestBody Demandeur demandeur) throws URISyntaxException {
        log.debug("REST request to save Demandeur : {}", demandeur);
        if (demandeur.getId() != null) {
            throw new BadRequestAlertException("A new demandeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demandeur result = demandeurService.save(demandeur);
        return ResponseEntity.created(new URI("/api/demandeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demandeurs} : Updates an existing demandeur.
     *
     * @param demandeur the demandeur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demandeur,
     * or with status {@code 400 (Bad Request)} if the demandeur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demandeur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demandeurs")
    public ResponseEntity<Demandeur> updateDemandeur(@Valid @RequestBody Demandeur demandeur) throws URISyntaxException {
        log.debug("REST request to update Demandeur : {}", demandeur);
        if (demandeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Demandeur result = demandeurService.save(demandeur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demandeur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /demandeurs} : get all the demandeurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demandeurs in body.
     */
    @GetMapping("/demandeurs")
    public List<Demandeur> getAllDemandeurs() {
        log.debug("REST request to get all Demandeurs");
        return demandeurService.findAll();
    }

    /**
     * {@code GET  /demandeurs/:id} : get the "id" demandeur.
     *
     * @param id the id of the demandeur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demandeur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demandeurs/{id}")
    public ResponseEntity<Demandeur> getDemandeur(@PathVariable Long id) {
        log.debug("REST request to get Demandeur : {}", id);
        Optional<Demandeur> demandeur = demandeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeur);
    }

    /**
     * {@code DELETE  /demandeurs/:id} : delete the "id" demandeur.
     *
     * @param id the id of the demandeur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demandeurs/{id}")
    public ResponseEntity<Void> deleteDemandeur(@PathVariable Long id) {
        log.debug("REST request to delete Demandeur : {}", id);
        demandeurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
