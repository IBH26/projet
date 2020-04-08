package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SFTP.
 */
@Entity
@Table(name = "sftp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SFTP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "format_transfer")
    private String formatTransfer;

    @Column(name = "jhi_user")
    private String user;

    @Column(name = "key")
    private String key;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormatTransfer() {
        return formatTransfer;
    }

    public SFTP formatTransfer(String formatTransfer) {
        this.formatTransfer = formatTransfer;
        return this;
    }

    public void setFormatTransfer(String formatTransfer) {
        this.formatTransfer = formatTransfer;
    }

    public String getUser() {
        return user;
    }

    public SFTP user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public SFTP key(String key) {
        this.key = key;
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SFTP)) {
            return false;
        }
        return id != null && id.equals(((SFTP) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SFTP{" +
            "id=" + getId() +
            ", formatTransfer='" + getFormatTransfer() + "'" +
            ", user='" + getUser() + "'" +
            ", key='" + getKey() + "'" +
            "}";
    }
}
