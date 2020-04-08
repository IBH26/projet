package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ContactProjet;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ContactProjet}.
 */
public interface ContactProjetService {

    /**
     * Save a contactProjet.
     *
     * @param contactProjet the entity to save.
     * @return the persisted entity.
     */
    ContactProjet save(ContactProjet contactProjet);

    /**
     * Get all the contactProjets.
     *
     * @return the list of entities.
     */
    List<ContactProjet> findAll();

    /**
     * Get the "id" contactProjet.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactProjet> findOne(Long id);

    /**
     * Delete the "id" contactProjet.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
