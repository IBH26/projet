package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ROUTAGEService;
import com.mycompany.myapp.domain.ROUTAGE;
import com.mycompany.myapp.repository.ROUTAGERepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ROUTAGE}.
 */
@Service
@Transactional
public class ROUTAGEServiceImpl implements ROUTAGEService {

    private final Logger log = LoggerFactory.getLogger(ROUTAGEServiceImpl.class);

    private final ROUTAGERepository rOUTAGERepository;

    public ROUTAGEServiceImpl(ROUTAGERepository rOUTAGERepository) {
        this.rOUTAGERepository = rOUTAGERepository;
    }

    /**
     * Save a rOUTAGE.
     *
     * @param rOUTAGE the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ROUTAGE save(ROUTAGE rOUTAGE) {
        log.debug("Request to save ROUTAGE : {}", rOUTAGE);
        return rOUTAGERepository.save(rOUTAGE);
    }

    /**
     * Get all the rOUTAGES.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ROUTAGE> findAll() {
        log.debug("Request to get all ROUTAGES");
        return rOUTAGERepository.findAll();
    }

    /**
     * Get one rOUTAGE by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ROUTAGE> findOne(Long id) {
        log.debug("Request to get ROUTAGE : {}", id);
        return rOUTAGERepository.findById(id);
    }

    /**
     * Delete the rOUTAGE by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ROUTAGE : {}", id);
        rOUTAGERepository.deleteById(id);
    }
}
