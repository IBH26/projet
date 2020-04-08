package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ContactProjetService;
import com.mycompany.myapp.domain.ContactProjet;
import com.mycompany.myapp.repository.ContactProjetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ContactProjet}.
 */
@Service
@Transactional
public class ContactProjetServiceImpl implements ContactProjetService {

    private final Logger log = LoggerFactory.getLogger(ContactProjetServiceImpl.class);

    private final ContactProjetRepository contactProjetRepository;

    public ContactProjetServiceImpl(ContactProjetRepository contactProjetRepository) {
        this.contactProjetRepository = contactProjetRepository;
    }

    /**
     * Save a contactProjet.
     *
     * @param contactProjet the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContactProjet save(ContactProjet contactProjet) {
        log.debug("Request to save ContactProjet : {}", contactProjet);
        return contactProjetRepository.save(contactProjet);
    }

    /**
     * Get all the contactProjets.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactProjet> findAll() {
        log.debug("Request to get all ContactProjets");
        return contactProjetRepository.findAll();
    }

    /**
     * Get one contactProjet by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactProjet> findOne(Long id) {
        log.debug("Request to get ContactProjet : {}", id);
        return contactProjetRepository.findById(id);
    }

    /**
     * Delete the contactProjet by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactProjet : {}", id);
        contactProjetRepository.deleteById(id);
    }
}
