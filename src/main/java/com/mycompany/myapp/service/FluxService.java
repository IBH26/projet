package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Flux;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Flux}.
 */
public interface FluxService {

    /**
     * Save a flux.
     *
     * @param flux the entity to save.
     * @return the persisted entity.
     */
    Flux save(Flux flux);

    /**
     * Get all the fluxes.
     *
     * @return the list of entities.
     */
    List<Flux> findAll();

    /**
     * Get the "id" flux.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Flux> findOne(Long id);

    /**
     * Delete the "id" flux.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
