package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.PESITService;
import com.mycompany.myapp.domain.PESIT;
import com.mycompany.myapp.repository.PESITRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PESIT}.
 */
@Service
@Transactional
public class PESITServiceImpl implements PESITService {

    private final Logger log = LoggerFactory.getLogger(PESITServiceImpl.class);

    private final PESITRepository pESITRepository;

    public PESITServiceImpl(PESITRepository pESITRepository) {
        this.pESITRepository = pESITRepository;
    }

    /**
     * Save a pESIT.
     *
     * @param pESIT the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PESIT save(PESIT pESIT) {
        log.debug("Request to save PESIT : {}", pESIT);
        return pESITRepository.save(pESIT);
    }

    /**
     * Get all the pESITS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PESIT> findAll() {
        log.debug("Request to get all PESITS");
        return pESITRepository.findAll();
    }

    /**
     * Get one pESIT by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PESIT> findOne(Long id) {
        log.debug("Request to get PESIT : {}", id);
        return pESITRepository.findById(id);
    }

    /**
     * Delete the pESIT by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PESIT : {}", id);
        pESITRepository.deleteById(id);
    }
}
