package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ContactTechnique;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ContactTechnique}.
 */
public interface ContactTechniqueService {

    /**
     * Save a contactTechnique.
     *
     * @param contactTechnique the entity to save.
     * @return the persisted entity.
     */
    ContactTechnique save(ContactTechnique contactTechnique);

    /**
     * Get all the contactTechniques.
     *
     * @return the list of entities.
     */
    List<ContactTechnique> findAll();

    /**
     * Get the "id" contactTechnique.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactTechnique> findOne(Long id);

    /**
     * Delete the "id" contactTechnique.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
