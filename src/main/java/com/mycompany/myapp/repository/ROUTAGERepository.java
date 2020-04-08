package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ROUTAGE;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ROUTAGE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ROUTAGERepository extends JpaRepository<ROUTAGE, Long> {

}
