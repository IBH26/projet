package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Env;

/**
 * A Flux.
 */
@Entity
@Table(name = "flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Flux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code_mega", nullable = false)
    private String codeMega;

    @Enumerated(EnumType.STRING)
    @Column(name = "envir")
    private Env envir;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "flux")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DemiFlux> demiFluxes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeMega() {
        return codeMega;
    }

    public Flux codeMega(String codeMega) {
        this.codeMega = codeMega;
        return this;
    }

    public void setCodeMega(String codeMega) {
        this.codeMega = codeMega;
    }

    public Env getEnvir() {
        return envir;
    }

    public Flux envir(Env envir) {
        this.envir = envir;
        return this;
    }

    public void setEnvir(Env envir) {
        this.envir = envir;
    }

    public Set<DemiFlux> getDemiFluxes() {
        return demiFluxes;
    }

    public Flux demiFluxes(Set<DemiFlux> demiFluxes) {
        this.demiFluxes = demiFluxes;
        return this;
    }

    public Flux addDemiFlux(DemiFlux demiFlux) {
        this.demiFluxes.add(demiFlux);
        demiFlux.setFlux(this);
        return this;
    }

    public Flux removeDemiFlux(DemiFlux demiFlux) {
        this.demiFluxes.remove(demiFlux);
        demiFlux.setFlux(null);
        return this;
    }

    public void setDemiFluxes(Set<DemiFlux> demiFluxes) {
        this.demiFluxes = demiFluxes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flux)) {
            return false;
        }
        return id != null && id.equals(((Flux) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Flux{" +
            "id=" + getId() +
            ", codeMega='" + getCodeMega() + "'" +
            ", envir='" + getEnvir() + "'" +
            "}";
    }
}
