package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ContactTechnique;
import com.mycompany.myapp.service.ContactTechniqueService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ContactTechnique}.
 */
@RestController
@RequestMapping("/api")
public class ContactTechniqueResource {

    private final Logger log = LoggerFactory.getLogger(ContactTechniqueResource.class);

    private static final String ENTITY_NAME = "contactTechnique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactTechniqueService contactTechniqueService;

    public ContactTechniqueResource(ContactTechniqueService contactTechniqueService) {
        this.contactTechniqueService = contactTechniqueService;
    }

    /**
     * {@code POST  /contact-techniques} : Create a new contactTechnique.
     *
     * @param contactTechnique the contactTechnique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactTechnique, or with status {@code 400 (Bad Request)} if the contactTechnique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-techniques")
    public ResponseEntity<ContactTechnique> createContactTechnique(@Valid @RequestBody ContactTechnique contactTechnique) throws URISyntaxException {
        log.debug("REST request to save ContactTechnique : {}", contactTechnique);
        if (contactTechnique.getId() != null) {
            throw new BadRequestAlertException("A new contactTechnique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactTechnique result = contactTechniqueService.save(contactTechnique);
        return ResponseEntity.created(new URI("/api/contact-techniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-techniques} : Updates an existing contactTechnique.
     *
     * @param contactTechnique the contactTechnique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactTechnique,
     * or with status {@code 400 (Bad Request)} if the contactTechnique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactTechnique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-techniques")
    public ResponseEntity<ContactTechnique> updateContactTechnique(@Valid @RequestBody ContactTechnique contactTechnique) throws URISyntaxException {
        log.debug("REST request to update ContactTechnique : {}", contactTechnique);
        if (contactTechnique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactTechnique result = contactTechniqueService.save(contactTechnique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactTechnique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-techniques} : get all the contactTechniques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactTechniques in body.
     */
    @GetMapping("/contact-techniques")
    public List<ContactTechnique> getAllContactTechniques() {
        log.debug("REST request to get all ContactTechniques");
        return contactTechniqueService.findAll();
    }

    /**
     * {@code GET  /contact-techniques/:id} : get the "id" contactTechnique.
     *
     * @param id the id of the contactTechnique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactTechnique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-techniques/{id}")
    public ResponseEntity<ContactTechnique> getContactTechnique(@PathVariable Long id) {
        log.debug("REST request to get ContactTechnique : {}", id);
        Optional<ContactTechnique> contactTechnique = contactTechniqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contactTechnique);
    }

    /**
     * {@code DELETE  /contact-techniques/:id} : delete the "id" contactTechnique.
     *
     * @param id the id of the contactTechnique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-techniques/{id}")
    public ResponseEntity<Void> deleteContactTechnique(@PathVariable Long id) {
        log.debug("REST request to delete ContactTechnique : {}", id);
        contactTechniqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
