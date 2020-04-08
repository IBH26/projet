package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PESIT;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PESIT entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PESITRepository extends JpaRepository<PESIT, Long> {

}
