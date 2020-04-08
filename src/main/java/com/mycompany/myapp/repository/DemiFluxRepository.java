package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DemiFlux;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DemiFlux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemiFluxRepository extends JpaRepository<DemiFlux, Long>, JpaSpecificationExecutor<DemiFlux> {

}
