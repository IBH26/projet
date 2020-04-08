package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ContactTechnique.
 */
@Entity
@Table(name = "contact_technique")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactTechnique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name_ct", nullable = false)
    private String nameCT;

    @NotNull
    @Column(name = "function_ct", nullable = false)
    private String functionCT;

    @Column(name = "phone_number_ct")
    private String phoneNumberCT;

    @Column(name = "email_ct")
    private String emailCT;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCT() {
        return nameCT;
    }

    public ContactTechnique nameCT(String nameCT) {
        this.nameCT = nameCT;
        return this;
    }

    public void setNameCT(String nameCT) {
        this.nameCT = nameCT;
    }

    public String getFunctionCT() {
        return functionCT;
    }

    public ContactTechnique functionCT(String functionCT) {
        this.functionCT = functionCT;
        return this;
    }

    public void setFunctionCT(String functionCT) {
        this.functionCT = functionCT;
    }

    public String getPhoneNumberCT() {
        return phoneNumberCT;
    }

    public ContactTechnique phoneNumberCT(String phoneNumberCT) {
        this.phoneNumberCT = phoneNumberCT;
        return this;
    }

    public void setPhoneNumberCT(String phoneNumberCT) {
        this.phoneNumberCT = phoneNumberCT;
    }

    public String getEmailCT() {
        return emailCT;
    }

    public ContactTechnique emailCT(String emailCT) {
        this.emailCT = emailCT;
        return this;
    }

    public void setEmailCT(String emailCT) {
        this.emailCT = emailCT;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactTechnique)) {
            return false;
        }
        return id != null && id.equals(((ContactTechnique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ContactTechnique{" +
            "id=" + getId() +
            ", nameCT='" + getNameCT() + "'" +
            ", functionCT='" + getFunctionCT() + "'" +
            ", phoneNumberCT='" + getPhoneNumberCT() + "'" +
            ", emailCT='" + getEmailCT() + "'" +
            "}";
    }
}
