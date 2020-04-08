package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ContactProjet.
 */
@Entity
@Table(name = "contact_projet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactProjet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name_cp", nullable = false)
    private String nameCP;

    @NotNull
    @Column(name = "function_cp", nullable = false)
    private String functionCP;

    @Column(name = "phone_number_cp")
    private String phoneNumberCP;

    @Column(name = "email_cp")
    private String emailCP;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCP() {
        return nameCP;
    }

    public ContactProjet nameCP(String nameCP) {
        this.nameCP = nameCP;
        return this;
    }

    public void setNameCP(String nameCP) {
        this.nameCP = nameCP;
    }

    public String getFunctionCP() {
        return functionCP;
    }

    public ContactProjet functionCP(String functionCP) {
        this.functionCP = functionCP;
        return this;
    }

    public void setFunctionCP(String functionCP) {
        this.functionCP = functionCP;
    }

    public String getPhoneNumberCP() {
        return phoneNumberCP;
    }

    public ContactProjet phoneNumberCP(String phoneNumberCP) {
        this.phoneNumberCP = phoneNumberCP;
        return this;
    }

    public void setPhoneNumberCP(String phoneNumberCP) {
        this.phoneNumberCP = phoneNumberCP;
    }

    public String getEmailCP() {
        return emailCP;
    }

    public ContactProjet emailCP(String emailCP) {
        this.emailCP = emailCP;
        return this;
    }

    public void setEmailCP(String emailCP) {
        this.emailCP = emailCP;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactProjet)) {
            return false;
        }
        return id != null && id.equals(((ContactProjet) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ContactProjet{" +
            "id=" + getId() +
            ", nameCP='" + getNameCP() + "'" +
            ", functionCP='" + getFunctionCP() + "'" +
            ", phoneNumberCP='" + getPhoneNumberCP() + "'" +
            ", emailCP='" + getEmailCP() + "'" +
            "}";
    }
}
