package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ContactTechnique;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ContactTechnique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactTechniqueRepository extends JpaRepository<ContactTechnique, Long> {

}
