package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ROUTAGE;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ROUTAGE}.
 */
public interface ROUTAGEService {

    /**
     * Save a rOUTAGE.
     *
     * @param rOUTAGE the entity to save.
     * @return the persisted entity.
     */
    ROUTAGE save(ROUTAGE rOUTAGE);

    /**
     * Get all the rOUTAGES.
     *
     * @return the list of entities.
     */
    List<ROUTAGE> findAll();

    /**
     * Get the "id" rOUTAGE.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ROUTAGE> findOne(Long id);

    /**
     * Delete the "id" rOUTAGE.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
