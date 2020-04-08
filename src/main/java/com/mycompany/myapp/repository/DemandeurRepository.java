package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Demandeur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Demandeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeurRepository extends JpaRepository<Demandeur, Long> {

}
