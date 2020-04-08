package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.mycompany.myapp.domain.enumeration.FHU;
import com.mycompany.myapp.domain.enumeration.Mode;
import com.mycompany.myapp.domain.enumeration.Type;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.DemiFlux} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.DemiFluxResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /demi-fluxes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DemiFluxCriteria implements Serializable, Criteria {
    /**
     * Class for filtering FHU
     */
    public static class FHUFilter extends Filter<FHU> {

        public FHUFilter() {
        }

        public FHUFilter(FHUFilter filter) {
            super(filter);
        }

        @Override
        public FHUFilter copy() {
            return new FHUFilter(this);
        }

    }
    /**
     * Class for filtering Mode
     */
    public static class ModeFilter extends Filter<Mode> {

        public ModeFilter() {
        }

        public ModeFilter(ModeFilter filter) {
            super(filter);
        }

        @Override
        public ModeFilter copy() {
            return new ModeFilter(this);
        }

    }
    /**
     * Class for filtering Type
     */
    public static class TypeFilter extends Filter<Type> {

        public TypeFilter() {
        }

        public TypeFilter(TypeFilter filter) {
            super(filter);
        }

        @Override
        public TypeFilter copy() {
            return new TypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter appliname;

    private FHUFilter partenairelocal;

    private StringFilter partenairedistant;

    private StringFilter directory;

    private StringFilter filename;

    private ModeFilter mode;

    private TypeFilter typology;

    private StringFilter type;

    private StringFilter hostname;

    private IntegerFilter port;

    private LongFilter contactProjetId;

    private LongFilter contactTechniqueId;

    private LongFilter pESITId;

    private LongFilter sFTPId;

    private LongFilter routageId;

    private LongFilter demandeurId;

    private LongFilter fluxId;

    public DemiFluxCriteria() {
    }

    public DemiFluxCriteria(DemiFluxCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.appliname = other.appliname == null ? null : other.appliname.copy();
        this.partenairelocal = other.partenairelocal == null ? null : other.partenairelocal.copy();
        this.partenairedistant = other.partenairedistant == null ? null : other.partenairedistant.copy();
        this.directory = other.directory == null ? null : other.directory.copy();
        this.filename = other.filename == null ? null : other.filename.copy();
        this.mode = other.mode == null ? null : other.mode.copy();
        this.typology = other.typology == null ? null : other.typology.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.hostname = other.hostname == null ? null : other.hostname.copy();
        this.port = other.port == null ? null : other.port.copy();
        this.contactProjetId = other.contactProjetId == null ? null : other.contactProjetId.copy();
        this.contactTechniqueId = other.contactTechniqueId == null ? null : other.contactTechniqueId.copy();
        this.pESITId = other.pESITId == null ? null : other.pESITId.copy();
        this.sFTPId = other.sFTPId == null ? null : other.sFTPId.copy();
        this.routageId = other.routageId == null ? null : other.routageId.copy();
        this.demandeurId = other.demandeurId == null ? null : other.demandeurId.copy();
        this.fluxId = other.fluxId == null ? null : other.fluxId.copy();
    }

    @Override
    public DemiFluxCriteria copy() {
        return new DemiFluxCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAppliname() {
        return appliname;
    }

    public void setAppliname(StringFilter appliname) {
        this.appliname = appliname;
    }

    public FHUFilter getPartenairelocal() {
        return partenairelocal;
    }

    public void setPartenairelocal(FHUFilter partenairelocal) {
        this.partenairelocal = partenairelocal;
    }

    public StringFilter getPartenairedistant() {
        return partenairedistant;
    }

    public void setPartenairedistant(StringFilter partenairedistant) {
        this.partenairedistant = partenairedistant;
    }

    public StringFilter getDirectory() {
        return directory;
    }

    public void setDirectory(StringFilter directory) {
        this.directory = directory;
    }

    public StringFilter getFilename() {
        return filename;
    }

    public void setFilename(StringFilter filename) {
        this.filename = filename;
    }

    public ModeFilter getMode() {
        return mode;
    }

    public void setMode(ModeFilter mode) {
        this.mode = mode;
    }

    public TypeFilter getTypology() {
        return typology;
    }

    public void setTypology(TypeFilter typology) {
        this.typology = typology;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getHostname() {
        return hostname;
    }

    public void setHostname(StringFilter hostname) {
        this.hostname = hostname;
    }

    public IntegerFilter getPort() {
        return port;
    }

    public void setPort(IntegerFilter port) {
        this.port = port;
    }

    public LongFilter getContactProjetId() {
        return contactProjetId;
    }

    public void setContactProjetId(LongFilter contactProjetId) {
        this.contactProjetId = contactProjetId;
    }

    public LongFilter getContactTechniqueId() {
        return contactTechniqueId;
    }

    public void setContactTechniqueId(LongFilter contactTechniqueId) {
        this.contactTechniqueId = contactTechniqueId;
    }

    public LongFilter getPESITId() {
        return pESITId;
    }

    public void setPESITId(LongFilter pESITId) {
        this.pESITId = pESITId;
    }

    public LongFilter getSFTPId() {
        return sFTPId;
    }

    public void setSFTPId(LongFilter sFTPId) {
        this.sFTPId = sFTPId;
    }

    public LongFilter getRoutageId() {
        return routageId;
    }

    public void setRoutageId(LongFilter routageId) {
        this.routageId = routageId;
    }

    public LongFilter getDemandeurId() {
        return demandeurId;
    }

    public void setDemandeurId(LongFilter demandeurId) {
        this.demandeurId = demandeurId;
    }

    public LongFilter getFluxId() {
        return fluxId;
    }

    public void setFluxId(LongFilter fluxId) {
        this.fluxId = fluxId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DemiFluxCriteria that = (DemiFluxCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(appliname, that.appliname) &&
            Objects.equals(partenairelocal, that.partenairelocal) &&
            Objects.equals(partenairedistant, that.partenairedistant) &&
            Objects.equals(directory, that.directory) &&
            Objects.equals(filename, that.filename) &&
            Objects.equals(mode, that.mode) &&
            Objects.equals(typology, that.typology) &&
            Objects.equals(type, that.type) &&
            Objects.equals(hostname, that.hostname) &&
            Objects.equals(port, that.port) &&
            Objects.equals(contactProjetId, that.contactProjetId) &&
            Objects.equals(contactTechniqueId, that.contactTechniqueId) &&
            Objects.equals(pESITId, that.pESITId) &&
            Objects.equals(sFTPId, that.sFTPId) &&
            Objects.equals(routageId, that.routageId) &&
            Objects.equals(demandeurId, that.demandeurId) &&
            Objects.equals(fluxId, that.fluxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        appliname,
        partenairelocal,
        partenairedistant,
        directory,
        filename,
        mode,
        typology,
        type,
        hostname,
        port,
        contactProjetId,
        contactTechniqueId,
        pESITId,
        sFTPId,
        routageId,
        demandeurId,
        fluxId
        );
    }

    @Override
    public String toString() {
        return "DemiFluxCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (appliname != null ? "appliname=" + appliname + ", " : "") +
                (partenairelocal != null ? "partenairelocal=" + partenairelocal + ", " : "") +
                (partenairedistant != null ? "partenairedistant=" + partenairedistant + ", " : "") +
                (directory != null ? "directory=" + directory + ", " : "") +
                (filename != null ? "filename=" + filename + ", " : "") +
                (mode != null ? "mode=" + mode + ", " : "") +
                (typology != null ? "typology=" + typology + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (hostname != null ? "hostname=" + hostname + ", " : "") +
                (port != null ? "port=" + port + ", " : "") +
                (contactProjetId != null ? "contactProjetId=" + contactProjetId + ", " : "") +
                (contactTechniqueId != null ? "contactTechniqueId=" + contactTechniqueId + ", " : "") +
                (pESITId != null ? "pESITId=" + pESITId + ", " : "") +
                (sFTPId != null ? "sFTPId=" + sFTPId + ", " : "") +
                (routageId != null ? "routageId=" + routageId + ", " : "") +
                (demandeurId != null ? "demandeurId=" + demandeurId + ", " : "") +
                (fluxId != null ? "fluxId=" + fluxId + ", " : "") +
            "}";
    }

}
