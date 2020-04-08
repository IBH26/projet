package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ROUTAGE.
 */
@Entity
@Table(name = "routage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ROUTAGE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "dr_name", nullable = false)
    private String drName;

    @Column(name = "rename")
    private Boolean rename;

    @Column(name = "mask_file")
    private String maskFile;

    @Column(name = "directory")
    private String directory;

    @Column(name = "handlingtype")
    private String handlingtype;

    @ManyToOne
    @JsonIgnoreProperties("routages")
    private DemiFlux demiflux;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrName() {
        return drName;
    }

    public ROUTAGE drName(String drName) {
        this.drName = drName;
        return this;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public Boolean isRename() {
        return rename;
    }

    public ROUTAGE rename(Boolean rename) {
        this.rename = rename;
        return this;
    }

    public void setRename(Boolean rename) {
        this.rename = rename;
    }

    public String getMaskFile() {
        return maskFile;
    }

    public ROUTAGE maskFile(String maskFile) {
        this.maskFile = maskFile;
        return this;
    }

    public void setMaskFile(String maskFile) {
        this.maskFile = maskFile;
    }

    public String getDirectory() {
        return directory;
    }

    public ROUTAGE directory(String directory) {
        this.directory = directory;
        return this;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getHandlingtype() {
        return handlingtype;
    }

    public ROUTAGE handlingtype(String handlingtype) {
        this.handlingtype = handlingtype;
        return this;
    }

    public void setHandlingtype(String handlingtype) {
        this.handlingtype = handlingtype;
    }

    public DemiFlux getDemiflux() {
        return demiflux;
    }

    public ROUTAGE demiflux(DemiFlux demiFlux) {
        this.demiflux = demiFlux;
        return this;
    }

    public void setDemiflux(DemiFlux demiFlux) {
        this.demiflux = demiFlux;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ROUTAGE)) {
            return false;
        }
        return id != null && id.equals(((ROUTAGE) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ROUTAGE{" +
            "id=" + getId() +
            ", drName='" + getDrName() + "'" +
            ", rename='" + isRename() + "'" +
            ", maskFile='" + getMaskFile() + "'" +
            ", directory='" + getDirectory() + "'" +
            ", handlingtype='" + getHandlingtype() + "'" +
            "}";
    }
}
