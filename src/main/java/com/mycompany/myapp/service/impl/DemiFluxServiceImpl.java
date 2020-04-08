package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DemiFluxService;
import com.mycompany.myapp.domain.DemiFlux;
import com.mycompany.myapp.repository.DemiFluxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DemiFlux}.
 */
@Service
@Transactional
public class DemiFluxServiceImpl implements DemiFluxService {

    private final Logger log = LoggerFactory.getLogger(DemiFluxServiceImpl.class);

    private final DemiFluxRepository demiFluxRepository;

    public DemiFluxServiceImpl(DemiFluxRepository demiFluxRepository) {
        this.demiFluxRepository = demiFluxRepository;
    }

    /**
     * Save a demiFlux.
     *
     * @param demiFlux the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DemiFlux save(DemiFlux demiFlux) {
        log.debug("Request to save DemiFlux : {}", demiFlux);
        return demiFluxRepository.save(demiFlux);
    }

    /**
     * Get all the demiFluxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemiFlux> findAll(Pageable pageable) {
        log.debug("Request to get all DemiFluxes");
        return demiFluxRepository.findAll(pageable);
    }

    /**
     * Get one demiFlux by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemiFlux> findOne(Long id) {
        log.debug("Request to get DemiFlux : {}", id);
        return demiFluxRepository.findById(id);
    }

    

    /**
     * Delete the demiFlux by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DemiFlux : {}", id);
        demiFluxRepository.deleteById(id);
    }
}
