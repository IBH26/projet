package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SFTPService;
import com.mycompany.myapp.domain.SFTP;
import com.mycompany.myapp.repository.SFTPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link SFTP}.
 */
@Service
@Transactional
public class SFTPServiceImpl implements SFTPService {

    private final Logger log = LoggerFactory.getLogger(SFTPServiceImpl.class);

    private final SFTPRepository sFTPRepository;

    public SFTPServiceImpl(SFTPRepository sFTPRepository) {
        this.sFTPRepository = sFTPRepository;
    }

    /**
     * Save a sFTP.
     *
     * @param sFTP the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SFTP save(SFTP sFTP) {
        log.debug("Request to save SFTP : {}", sFTP);
        return sFTPRepository.save(sFTP);
    }

    /**
     * Get all the sFTPS.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SFTP> findAll() {
        log.debug("Request to get all SFTPS");
        return sFTPRepository.findAll();
    }

    /**
     * Get one sFTP by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SFTP> findOne(Long id) {
        log.debug("Request to get SFTP : {}", id);
        return sFTPRepository.findById(id);
    }

    /**
     * Delete the sFTP by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SFTP : {}", id);
        sFTPRepository.deleteById(id);
    }
}
