package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.mycompany.myapp.domain.enumeration.Env;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Flux} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.FluxResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /fluxes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FluxCriteria implements Serializable, Criteria {
    /**
     * Class for filtering Env
     */
    public static class EnvFilter extends Filter<Env> {

        public EnvFilter() {
        }

        public EnvFilter(EnvFilter filter) {
            super(filter);
        }

        @Override
        public EnvFilter copy() {
            return new EnvFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter codeMega;

    private EnvFilter envir;

    private LongFilter demiFluxId;

    public FluxCriteria() {
    }

    public FluxCriteria(FluxCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.codeMega = other.codeMega == null ? null : other.codeMega.copy();
        this.envir = other.envir == null ? null : other.envir.copy();
        this.demiFluxId = other.demiFluxId == null ? null : other.demiFluxId.copy();
    }

    @Override
    public FluxCriteria copy() {
        return new FluxCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCodeMega() {
        return codeMega;
    }

    public void setCodeMega(StringFilter codeMega) {
        this.codeMega = codeMega;
    }

    public EnvFilter getEnvir() {
        return envir;
    }

    public void setEnvir(EnvFilter envir) {
        this.envir = envir;
    }

    public LongFilter getDemiFluxId() {
        return demiFluxId;
    }

    public void setDemiFluxId(LongFilter demiFluxId) {
        this.demiFluxId = demiFluxId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FluxCriteria that = (FluxCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codeMega, that.codeMega) &&
            Objects.equals(envir, that.envir) &&
            Objects.equals(demiFluxId, that.demiFluxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codeMega,
        envir,
        demiFluxId
        );
    }

    @Override
    public String toString() {
        return "FluxCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codeMega != null ? "codeMega=" + codeMega + ", " : "") +
                (envir != null ? "envir=" + envir + ", " : "") +
                (demiFluxId != null ? "demiFluxId=" + demiFluxId + ", " : "") +
            "}";
    }

}
