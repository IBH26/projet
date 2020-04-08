package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SFTP;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SFTP}.
 */
public interface SFTPService {

    /**
     * Save a sFTP.
     *
     * @param sFTP the entity to save.
     * @return the persisted entity.
     */
    SFTP save(SFTP sFTP);

    /**
     * Get all the sFTPS.
     *
     * @return the list of entities.
     */
    List<SFTP> findAll();

    /**
     * Get the "id" sFTP.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SFTP> findOne(Long id);

    /**
     * Delete the "id" sFTP.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
