package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ContactProjet;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ContactProjet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactProjetRepository extends JpaRepository<ContactProjet, Long> {

}
