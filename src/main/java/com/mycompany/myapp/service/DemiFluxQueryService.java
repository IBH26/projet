package com.mycompany.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.mycompany.myapp.domain.DemiFlux;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.DemiFluxRepository;
import com.mycompany.myapp.service.dto.DemiFluxCriteria;

/**
 * Service for executing complex queries for {@link DemiFlux} entities in the database.
 * The main input is a {@link DemiFluxCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DemiFlux} or a {@link Page} of {@link DemiFlux} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DemiFluxQueryService extends QueryService<DemiFlux> {

    private final Logger log = LoggerFactory.getLogger(DemiFluxQueryService.class);

    private final DemiFluxRepository demiFluxRepository;

    public DemiFluxQueryService(DemiFluxRepository demiFluxRepository) {
        this.demiFluxRepository = demiFluxRepository;
    }

    /**
     * Return a {@link List} of {@link DemiFlux} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DemiFlux> findByCriteria(DemiFluxCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DemiFlux> specification = createSpecification(criteria);
        return demiFluxRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link DemiFlux} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DemiFlux> findByCriteria(DemiFluxCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DemiFlux> specification = createSpecification(criteria);
        return demiFluxRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DemiFluxCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DemiFlux> specification = createSpecification(criteria);
        return demiFluxRepository.count(specification);
    }

    /**
     * Function to convert {@link DemiFluxCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DemiFlux> createSpecification(DemiFluxCriteria criteria) {
        Specification<DemiFlux> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DemiFlux_.id));
            }
            if (criteria.getAppliname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAppliname(), DemiFlux_.appliname));
            }
            if (criteria.getPartenairelocal() != null) {
                specification = specification.and(buildSpecification(criteria.getPartenairelocal(), DemiFlux_.partenairelocal));
            }
            if (criteria.getPartenairedistant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPartenairedistant(), DemiFlux_.partenairedistant));
            }
            if (criteria.getDirectory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDirectory(), DemiFlux_.directory));
            }
            if (criteria.getFilename() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFilename(), DemiFlux_.filename));
            }
            if (criteria.getMode() != null) {
                specification = specification.and(buildSpecification(criteria.getMode(), DemiFlux_.mode));
            }
            if (criteria.getTypology() != null) {
                specification = specification.and(buildSpecification(criteria.getTypology(), DemiFlux_.typology));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), DemiFlux_.type));
            }
            if (criteria.getHostname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHostname(), DemiFlux_.hostname));
            }
            if (criteria.getPort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPort(), DemiFlux_.port));
            }
            if (criteria.getContactProjetId() != null) {
                specification = specification.and(buildSpecification(criteria.getContactProjetId(),
                    root -> root.join(DemiFlux_.contactProjet, JoinType.LEFT).get(ContactProjet_.id)));
            }
            if (criteria.getContactTechniqueId() != null) {
                specification = specification.and(buildSpecification(criteria.getContactTechniqueId(),
                    root -> root.join(DemiFlux_.contactTechnique, JoinType.LEFT).get(ContactTechnique_.id)));
            }
            if (criteria.getPESITId() != null) {
                specification = specification.and(buildSpecification(criteria.getPESITId(),
                    root -> root.join(DemiFlux_.pESIT, JoinType.LEFT).get(PESIT_.id)));
            }
            if (criteria.getSFTPId() != null) {
                specification = specification.and(buildSpecification(criteria.getSFTPId(),
                    root -> root.join(DemiFlux_.sFTP, JoinType.LEFT).get(SFTP_.id)));
            }
            if (criteria.getRoutageId() != null) {
                specification = specification.and(buildSpecification(criteria.getRoutageId(),
                    root -> root.join(DemiFlux_.routages, JoinType.LEFT).get(ROUTAGE_.id)));
            }
            if (criteria.getDemandeurId() != null) {
                specification = specification.and(buildSpecification(criteria.getDemandeurId(),
                    root -> root.join(DemiFlux_.demandeur, JoinType.LEFT).get(Demandeur_.id)));
            }
            if (criteria.getFluxId() != null) {
                specification = specification.and(buildSpecification(criteria.getFluxId(),
                    root -> root.join(DemiFlux_.flux, JoinType.LEFT).get(Flux_.id)));
            }
        }
        return specification;
    }
}
