package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Demandeur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Demandeur}.
 */
public interface DemandeurService {

    /**
     * Save a demandeur.
     *
     * @param demandeur the entity to save.
     * @return the persisted entity.
     */
    Demandeur save(Demandeur demandeur);

    /**
     * Get all the demandeurs.
     *
     * @return the list of entities.
     */
    List<Demandeur> findAll();

    /**
     * Get the "id" demandeur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Demandeur> findOne(Long id);

    /**
     * Delete the "id" demandeur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
