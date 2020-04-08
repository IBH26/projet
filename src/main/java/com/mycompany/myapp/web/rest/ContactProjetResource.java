package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ContactProjet;
import com.mycompany.myapp.service.ContactProjetService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ContactProjet}.
 */
@RestController
@RequestMapping("/api")
public class ContactProjetResource {

    private final Logger log = LoggerFactory.getLogger(ContactProjetResource.class);

    private static final String ENTITY_NAME = "contactProjet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactProjetService contactProjetService;

    public ContactProjetResource(ContactProjetService contactProjetService) {
        this.contactProjetService = contactProjetService;
    }

    /**
     * {@code POST  /contact-projets} : Create a new contactProjet.
     *
     * @param contactProjet the contactProjet to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactProjet, or with status {@code 400 (Bad Request)} if the contactProjet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-projets")
    public ResponseEntity<ContactProjet> createContactProjet(@Valid @RequestBody ContactProjet contactProjet) throws URISyntaxException {
        log.debug("REST request to save ContactProjet : {}", contactProjet);
        if (contactProjet.getId() != null) {
            throw new BadRequestAlertException("A new contactProjet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactProjet result = contactProjetService.save(contactProjet);
        return ResponseEntity.created(new URI("/api/contact-projets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-projets} : Updates an existing contactProjet.
     *
     * @param contactProjet the contactProjet to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactProjet,
     * or with status {@code 400 (Bad Request)} if the contactProjet is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactProjet couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-projets")
    public ResponseEntity<ContactProjet> updateContactProjet(@Valid @RequestBody ContactProjet contactProjet) throws URISyntaxException {
        log.debug("REST request to update ContactProjet : {}", contactProjet);
        if (contactProjet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactProjet result = contactProjetService.save(contactProjet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactProjet.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-projets} : get all the contactProjets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactProjets in body.
     */
    @GetMapping("/contact-projets")
    public List<ContactProjet> getAllContactProjets() {
        log.debug("REST request to get all ContactProjets");
        return contactProjetService.findAll();
    }

    /**
     * {@code GET  /contact-projets/:id} : get the "id" contactProjet.
     *
     * @param id the id of the contactProjet to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactProjet, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-projets/{id}")
    public ResponseEntity<ContactProjet> getContactProjet(@PathVariable Long id) {
        log.debug("REST request to get ContactProjet : {}", id);
        Optional<ContactProjet> contactProjet = contactProjetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactProjet);
    }

    /**
     * {@code DELETE  /contact-projets/:id} : delete the "id" contactProjet.
     *
     * @param id the id of the contactProjet to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-projets/{id}")
    public ResponseEntity<Void> deleteContactProjet(@PathVariable Long id) {
        log.debug("REST request to delete ContactProjet : {}", id);
        contactProjetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
