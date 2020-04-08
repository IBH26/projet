package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SFTP;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SFTP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SFTPRepository extends JpaRepository<SFTP, Long> {

}
