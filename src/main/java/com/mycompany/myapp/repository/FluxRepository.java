package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Flux;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Flux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FluxRepository extends JpaRepository<Flux, Long>, JpaSpecificationExecutor<Flux> {

}
