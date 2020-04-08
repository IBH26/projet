package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PESIT.
 */
@Entity
@Table(name = "pesit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PESIT implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_site")
    private String codeSite;

    @Column(name = "code_application")
    private String codeApplication;

    @Column(name = "transcoding")
    private String transcoding;

    @Column(name = "compression")
    private String compression;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeSite() {
        return codeSite;
    }

    public PESIT codeSite(String codeSite) {
        this.codeSite = codeSite;
        return this;
    }

    public void setCodeSite(String codeSite) {
        this.codeSite = codeSite;
    }

    public String getCodeApplication() {
        return codeApplication;
    }

    public PESIT codeApplication(String codeApplication) {
        this.codeApplication = codeApplication;
        return this;
    }

    public void setCodeApplication(String codeApplication) {
        this.codeApplication = codeApplication;
    }

    public String getTranscoding() {
        return transcoding;
    }

    public PESIT transcoding(String transcoding) {
        this.transcoding = transcoding;
        return this;
    }

    public void setTranscoding(String transcoding) {
        this.transcoding = transcoding;
    }

    public String getCompression() {
        return compression;
    }

    public PESIT compression(String compression) {
        this.compression = compression;
        return this;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PESIT)) {
            return false;
        }
        return id != null && id.equals(((PESIT) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PESIT{" +
            "id=" + getId() +
            ", codeSite='" + getCodeSite() + "'" +
            ", codeApplication='" + getCodeApplication() + "'" +
            ", transcoding='" + getTranscoding() + "'" +
            ", compression='" + getCompression() + "'" +
            "}";
    }
}
