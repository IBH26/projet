package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ContactTechniqueService;
import com.mycompany.myapp.domain.ContactTechnique;
import com.mycompany.myapp.repository.ContactTechniqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ContactTechnique}.
 */
@Service
@Transactional
public class ContactTechniqueServiceImpl implements ContactTechniqueService {

    private final Logger log = LoggerFactory.getLogger(ContactTechniqueServiceImpl.class);

    private final ContactTechniqueRepository contactTechniqueRepository;

    public ContactTechniqueServiceImpl(ContactTechniqueRepository contactTechniqueRepository) {
        this.contactTechniqueRepository = contactTechniqueRepository;
    }

    /**
     * Save a contactTechnique.
     *
     * @param contactTechnique the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContactTechnique save(ContactTechnique contactTechnique) {
        log.debug("Request to save ContactTechnique : {}", contactTechnique);
        return contactTechniqueRepository.save(contactTechnique);
    }

    /**
     * Get all the contactTechniques.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContactTechnique> findAll() {
        log.debug("Request to get all ContactTechniques");
        return contactTechniqueRepository.findAll();
    }

    /**
     * Get one contactTechnique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContactTechnique> findOne(Long id) {
        log.debug("Request to get ContactTechnique : {}", id);
        return contactTechniqueRepository.findById(id);
    }

    /**
     * Delete the contactTechnique by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContactTechnique : {}", id);
        contactTechniqueRepository.deleteById(id);
    }
}
