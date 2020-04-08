package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.FHU;

import com.mycompany.myapp.domain.enumeration.Mode;

import com.mycompany.myapp.domain.enumeration.Type;

/**
 * A DemiFlux.
 */
@Entity
@Table(name = "demi_flux")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemiFlux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "appliname")
    private String appliname;

    @Enumerated(EnumType.STRING)
    @Column(name = "partenairelocal")
    private FHU partenairelocal;

    @Column(name = "partenairedistant")
    private String partenairedistant;

    @Column(name = "directory")
    private String directory;

    @Column(name = "filename")
    private String filename;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private Mode mode;

    @Enumerated(EnumType.STRING)
    @Column(name = "typology")
    private Type typology;

    @Column(name = "type")
    private String type;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "port")
    private Integer port;

    @OneToOne
    @JoinColumn(unique = true)
    private ContactProjet contactProjet;

    @OneToOne
    @JoinColumn(unique = true)
    private ContactTechnique contactTechnique;

    @OneToOne
    @JoinColumn(unique = true)
    private PESIT pESIT;

    @OneToOne
    @JoinColumn(unique = true)
    private SFTP sFTP;

    @OneToMany(mappedBy = "demiflux")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ROUTAGE> routages = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("demiFluxes")
    private Demandeur demandeur;

    @ManyToOne
    @JsonIgnoreProperties("demiFluxes")
    private Flux flux;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppliname() {
        return appliname;
    }

    public DemiFlux appliname(String appliname) {
        this.appliname = appliname;
        return this;
    }

    public void setAppliname(String appliname) {
        this.appliname = appliname;
    }

    public FHU getPartenairelocal() {
        return partenairelocal;
    }

    public DemiFlux partenairelocal(FHU partenairelocal) {
        this.partenairelocal = partenairelocal;
        return this;
    }

    public void setPartenairelocal(FHU partenairelocal) {
        this.partenairelocal = partenairelocal;
    }

    public String getPartenairedistant() {
        return partenairedistant;
    }

    public DemiFlux partenairedistant(String partenairedistant) {
        this.partenairedistant = partenairedistant;
        return this;
    }

    public void setPartenairedistant(String partenairedistant) {
        this.partenairedistant = partenairedistant;
    }

    public String getDirectory() {
        return directory;
    }

    public DemiFlux directory(String directory) {
        this.directory = directory;
        return this;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFilename() {
        return filename;
    }

    public DemiFlux filename(String filename) {
        this.filename = filename;
        return this;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Mode getMode() {
        return mode;
    }

    public DemiFlux mode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Type getTypology() {
        return typology;
    }

    public DemiFlux typology(Type typology) {
        this.typology = typology;
        return this;
    }

    public void setTypology(Type typology) {
        this.typology = typology;
    }

    public String getType() {
        return type;
    }

    public DemiFlux type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHostname() {
        return hostname;
    }

    public DemiFlux hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public DemiFlux port(Integer port) {
        this.port = port;
        return this;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public ContactProjet getContactProjet() {
        return contactProjet;
    }

    public DemiFlux contactProjet(ContactProjet contactProjet) {
        this.contactProjet = contactProjet;
        return this;
    }

    public void setContactProjet(ContactProjet contactProjet) {
        this.contactProjet = contactProjet;
    }

    public ContactTechnique getContactTechnique() {
        return contactTechnique;
    }

    public DemiFlux contactTechnique(ContactTechnique contactTechnique) {
        this.contactTechnique = contactTechnique;
        return this;
    }

    public void setContactTechnique(ContactTechnique contactTechnique) {
        this.contactTechnique = contactTechnique;
    }

    public PESIT getPESIT() {
        return pESIT;
    }

    public DemiFlux pESIT(PESIT pESIT) {
        this.pESIT = pESIT;
        return this;
    }

    public void setPESIT(PESIT pESIT) {
        this.pESIT = pESIT;
    }

    public SFTP getSFTP() {
        return sFTP;
    }

    public DemiFlux sFTP(SFTP sFTP) {
        this.sFTP = sFTP;
        return this;
    }

    public void setSFTP(SFTP sFTP) {
        this.sFTP = sFTP;
    }

    public Set<ROUTAGE> getRoutages() {
        return routages;
    }

    public DemiFlux routages(Set<ROUTAGE> rOUTAGES) {
        this.routages = rOUTAGES;
        return this;
    }

    public DemiFlux addRoutage(ROUTAGE rOUTAGE) {
        this.routages.add(rOUTAGE);
        rOUTAGE.setDemiflux(this);
        return this;
    }

    public DemiFlux removeRoutage(ROUTAGE rOUTAGE) {
        this.routages.remove(rOUTAGE);
        rOUTAGE.setDemiflux(null);
        return this;
    }

    public void setRoutages(Set<ROUTAGE> rOUTAGES) {
        this.routages = rOUTAGES;
    }

    public Demandeur getDemandeur() {
        return demandeur;
    }

    public DemiFlux demandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
        return this;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public Flux getFlux() {
        return flux;
    }

    public DemiFlux flux(Flux flux) {
        this.flux = flux;
        return this;
    }

    public void setFlux(Flux flux) {
        this.flux = flux;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemiFlux)) {
            return false;
        }
        return id != null && id.equals(((DemiFlux) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DemiFlux{" +
            "id=" + getId() +
            ", appliname='" + getAppliname() + "'" +
            ", partenairelocal='" + getPartenairelocal() + "'" +
            ", partenairedistant='" + getPartenairedistant() + "'" +
            ", directory='" + getDirectory() + "'" +
            ", filename='" + getFilename() + "'" +
            ", mode='" + getMode() + "'" +
            ", typology='" + getTypology() + "'" +
            ", type='" + getType() + "'" +
            ", hostname='" + getHostname() + "'" +
            ", port=" + getPort() +
            "}";
    }
}
