package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.FluxService;
import com.mycompany.myapp.domain.Flux;
import com.mycompany.myapp.repository.FluxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Flux}.
 */
@Service
@Transactional
public class FluxServiceImpl implements FluxService {

    private final Logger log = LoggerFactory.getLogger(FluxServiceImpl.class);

    private final FluxRepository fluxRepository;

    public FluxServiceImpl(FluxRepository fluxRepository) {
        this.fluxRepository = fluxRepository;
    }

    /**
     * Save a flux.
     *
     * @param flux the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Flux save(Flux flux) {
        log.debug("Request to save Flux : {}", flux);
        return fluxRepository.save(flux);
    }

    /**
     * Get all the fluxes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Flux> findAll() {
        log.debug("Request to get all Fluxes");
        return fluxRepository.findAll();
    }

    /**
     * Get one flux by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Flux> findOne(Long id) {
        log.debug("Request to get Flux : {}", id);
        return fluxRepository.findById(id);
    }

    /**
     * Delete the flux by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Flux : {}", id);
        fluxRepository.deleteById(id);
    }
}
