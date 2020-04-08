package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DemiFlux;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DemiFlux}.
 */
public interface DemiFluxService {

    /**
     * Save a demiFlux.
     *
     * @param demiFlux the entity to save.
     * @return the persisted entity.
     */
    DemiFlux save(DemiFlux demiFlux);

    /**
     * Get all the demiFluxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DemiFlux> findAll(Pageable pageable);

    /**
     * Get the "id" demiFlux.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DemiFlux> findOne(Long id);

    /**
     * Delete the "id" demiFlux.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    
}
