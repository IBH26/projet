package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DemandeurService;
import com.mycompany.myapp.domain.Demandeur;
import com.mycompany.myapp.repository.DemandeurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Demandeur}.
 */
@Service
@Transactional
public class DemandeurServiceImpl implements DemandeurService {

    private final Logger log = LoggerFactory.getLogger(DemandeurServiceImpl.class);

    private final DemandeurRepository demandeurRepository;

    public DemandeurServiceImpl(DemandeurRepository demandeurRepository) {
        this.demandeurRepository = demandeurRepository;
    }

    /**
     * Save a demandeur.
     *
     * @param demandeur the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Demandeur save(Demandeur demandeur) {
        log.debug("Request to save Demandeur : {}", demandeur);
        return demandeurRepository.save(demandeur);
    }

    /**
     * Get all the demandeurs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Demandeur> findAll() {
        log.debug("Request to get all Demandeurs");
        return demandeurRepository.findAll();
    }

    /**
     * Get one demandeur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Demandeur> findOne(Long id) {
        log.debug("Request to get Demandeur : {}", id);
        return demandeurRepository.findById(id);
    }

    /**
     * Delete the demandeur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demandeur : {}", id);
        demandeurRepository.deleteById(id);
    }
}
