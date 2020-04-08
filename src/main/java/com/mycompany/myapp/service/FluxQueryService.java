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

import com.mycompany.myapp.domain.Flux;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.repository.FluxRepository;
import com.mycompany.myapp.service.dto.FluxCriteria;

/**
 * Service for executing complex queries for {@link Flux} entities in the database.
 * The main input is a {@link FluxCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Flux} or a {@link Page} of {@link Flux} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FluxQueryService extends QueryService<Flux> {

    private final Logger log = LoggerFactory.getLogger(FluxQueryService.class);

    private final FluxRepository fluxRepository;

    public FluxQueryService(FluxRepository fluxRepository) {
        this.fluxRepository = fluxRepository;
    }

    /**
     * Return a {@link List} of {@link Flux} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Flux> findByCriteria(FluxCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Flux> specification = createSpecification(criteria);
        return fluxRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Flux} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Flux> findByCriteria(FluxCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Flux> specification = createSpecification(criteria);
        return fluxRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FluxCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Flux> specification = createSpecification(criteria);
        return fluxRepository.count(specification);
    }

    /**
     * Function to convert {@link FluxCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Flux> createSpecification(FluxCriteria criteria) {
        Specification<Flux> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Flux_.id));
            }
            if (criteria.getCodeMega() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodeMega(), Flux_.codeMega));
            }
            if (criteria.getEnvir() != null) {
                specification = specification.and(buildSpecification(criteria.getEnvir(), Flux_.envir));
            }
            if (criteria.getDemiFluxId() != null) {
                specification = specification.and(buildSpecification(criteria.getDemiFluxId(),
                    root -> root.join(Flux_.demiFluxes, JoinType.LEFT).get(DemiFlux_.id)));
            }
        }
        return specification;
    }
}
