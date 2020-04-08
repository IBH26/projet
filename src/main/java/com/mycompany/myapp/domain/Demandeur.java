package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Demandeur.
 */
@Entity
@Table(name = "demandeur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Demandeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name_d", nullable = false)
    private String nameD;

    @NotNull
    @Column(name = "function_d", nullable = false)
    private String functionD;

    @Column(name = "project_d")
    private String projectD;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameD() {
        return nameD;
    }

    public Demandeur nameD(String nameD) {
        this.nameD = nameD;
        return this;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public String getFunctionD() {
        return functionD;
    }

    public Demandeur functionD(String functionD) {
        this.functionD = functionD;
        return this;
    }

    public void setFunctionD(String functionD) {
        this.functionD = functionD;
    }

    public String getProjectD() {
        return projectD;
    }

    public Demandeur projectD(String projectD) {
        this.projectD = projectD;
        return this;
    }

    public void setProjectD(String projectD) {
        this.projectD = projectD;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demandeur)) {
            return false;
        }
        return id != null && id.equals(((Demandeur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Demandeur{" +
            "id=" + getId() +
            ", nameD='" + getNameD() + "'" +
            ", functionD='" + getFunctionD() + "'" +
            ", projectD='" + getProjectD() + "'" +
            "}";
    }
}
