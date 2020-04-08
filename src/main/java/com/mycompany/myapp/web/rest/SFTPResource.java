package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SFTP;
import com.mycompany.myapp.service.SFTPService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SFTP}.
 */
@RestController
@RequestMapping("/api")
public class SFTPResource {

    private final Logger log = LoggerFactory.getLogger(SFTPResource.class);

    private static final String ENTITY_NAME = "sFTP";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SFTPService sFTPService;

    public SFTPResource(SFTPService sFTPService) {
        this.sFTPService = sFTPService;
    }

    /**
     * {@code POST  /sftps} : Create a new sFTP.
     *
     * @param sFTP the sFTP to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sFTP, or with status {@code 400 (Bad Request)} if the sFTP has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sftps")
    public ResponseEntity<SFTP> createSFTP(@RequestBody SFTP sFTP) throws URISyntaxException {
        log.debug("REST request to save SFTP : {}", sFTP);
        if (sFTP.getId() != null) {
            throw new BadRequestAlertException("A new sFTP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SFTP result = sFTPService.save(sFTP);
        return ResponseEntity.created(new URI("/api/sftps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sftps} : Updates an existing sFTP.
     *
     * @param sFTP the sFTP to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sFTP,
     * or with status {@code 400 (Bad Request)} if the sFTP is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sFTP couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sftps")
    public ResponseEntity<SFTP> updateSFTP(@RequestBody SFTP sFTP) throws URISyntaxException {
        log.debug("REST request to update SFTP : {}", sFTP);
        if (sFTP.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SFTP result = sFTPService.save(sFTP);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sFTP.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sftps} : get all the sFTPS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sFTPS in body.
     */
    @GetMapping("/sftps")
    public List<SFTP> getAllSFTPS() {
        log.debug("REST request to get all SFTPS");
        return sFTPService.findAll();
    }

    /**
     * {@code GET  /sftps/:id} : get the "id" sFTP.
     *
     * @param id the id of the sFTP to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sFTP, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sftps/{id}")
    public ResponseEntity<SFTP> getSFTP(@PathVariable Long id) {
        log.debug("REST request to get SFTP : {}", id);
        Optional<SFTP> sFTP = sFTPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sFTP);
    }

    /**
     * {@code DELETE  /sftps/:id} : delete the "id" sFTP.
     *
     * @param id the id of the sFTP to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sftps/{id}")
    public ResponseEntity<Void> deleteSFTP(@PathVariable Long id) {
        log.debug("REST request to delete SFTP : {}", id);
        sFTPService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
