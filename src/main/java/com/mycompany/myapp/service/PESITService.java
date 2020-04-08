package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.PESIT;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PESIT}.
 */
public interface PESITService {

    /**
     * Save a pESIT.
     *
     * @param pESIT the entity to save.
     * @return the persisted entity.
     */
    PESIT save(PESIT pESIT);

    /**
     * Get all the pESITS.
     *
     * @return the list of entities.
     */
    List<PESIT> findAll();

    /**
     * Get the "id" pESIT.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PESIT> findOne(Long id);

    /**
     * Delete the "id" pESIT.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
