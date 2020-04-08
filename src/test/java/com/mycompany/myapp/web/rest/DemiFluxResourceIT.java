package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.DemiFlux;
import com.mycompany.myapp.domain.ContactProjet;
import com.mycompany.myapp.domain.ContactTechnique;
import com.mycompany.myapp.domain.PESIT;
import com.mycompany.myapp.domain.SFTP;
import com.mycompany.myapp.domain.ROUTAGE;
import com.mycompany.myapp.domain.Demandeur;
import com.mycompany.myapp.domain.Flux;
import com.mycompany.myapp.repository.DemiFluxRepository;
import com.mycompany.myapp.service.DemiFluxService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.DemiFluxCriteria;
import com.mycompany.myapp.service.DemiFluxQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.FHU;
import com.mycompany.myapp.domain.enumeration.Mode;
import com.mycompany.myapp.domain.enumeration.Type;
/**
 * Integration tests for the {@link DemiFluxResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class DemiFluxResourceIT {

    private static final String DEFAULT_APPLINAME = "AAAAAAAAAA";
    private static final String UPDATED_APPLINAME = "BBBBBBBBBB";

    private static final FHU DEFAULT_PARTENAIRELOCAL = FHU.FHU1;
    private static final FHU UPDATED_PARTENAIRELOCAL = FHU.FHU2;

    private static final String DEFAULT_PARTENAIREDISTANT = "AAAAAAAAAA";
    private static final String UPDATED_PARTENAIREDISTANT = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTORY = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTORY = "BBBBBBBBBB";

    private static final String DEFAULT_FILENAME = "AAAAAAAAAA";
    private static final String UPDATED_FILENAME = "BBBBBBBBBB";

    private static final Mode DEFAULT_MODE = Mode.PUSH;
    private static final Mode UPDATED_MODE = Mode.PULL;

    private static final Type DEFAULT_TYPOLOGY = Type.IN;
    private static final Type UPDATED_TYPOLOGY = Type.OUT;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_HOSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_HOSTNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PORT = 1;
    private static final Integer UPDATED_PORT = 2;
    private static final Integer SMALLER_PORT = 1 - 1;

    @Autowired
    private DemiFluxRepository demiFluxRepository;

    @Autowired
    private DemiFluxService demiFluxService;

    @Autowired
    private DemiFluxQueryService demiFluxQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDemiFluxMockMvc;

    private DemiFlux demiFlux;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DemiFluxResource demiFluxResource = new DemiFluxResource(demiFluxService, demiFluxQueryService);
        this.restDemiFluxMockMvc = MockMvcBuilders.standaloneSetup(demiFluxResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemiFlux createEntity(EntityManager em) {
        DemiFlux demiFlux = new DemiFlux()
            .appliname(DEFAULT_APPLINAME)
            .partenairelocal(DEFAULT_PARTENAIRELOCAL)
            .partenairedistant(DEFAULT_PARTENAIREDISTANT)
            .directory(DEFAULT_DIRECTORY)
            .filename(DEFAULT_FILENAME)
            .mode(DEFAULT_MODE)
            .typology(DEFAULT_TYPOLOGY)
            .type(DEFAULT_TYPE)
            .hostname(DEFAULT_HOSTNAME)
            .port(DEFAULT_PORT);
        // Add required entity
        Demandeur demandeur;
        if (TestUtil.findAll(em, Demandeur.class).isEmpty()) {
            demandeur = DemandeurResourceIT.createEntity(em);
            em.persist(demandeur);
            em.flush();
        } else {
            demandeur = TestUtil.findAll(em, Demandeur.class).get(0);
        }
        demiFlux.setDemandeur(demandeur);
        return demiFlux;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DemiFlux createUpdatedEntity(EntityManager em) {
        DemiFlux demiFlux = new DemiFlux()
            .appliname(UPDATED_APPLINAME)
            .partenairelocal(UPDATED_PARTENAIRELOCAL)
            .partenairedistant(UPDATED_PARTENAIREDISTANT)
            .directory(UPDATED_DIRECTORY)
            .filename(UPDATED_FILENAME)
            .mode(UPDATED_MODE)
            .typology(UPDATED_TYPOLOGY)
            .type(UPDATED_TYPE)
            .hostname(UPDATED_HOSTNAME)
            .port(UPDATED_PORT);
        // Add required entity
        Demandeur demandeur;
        if (TestUtil.findAll(em, Demandeur.class).isEmpty()) {
            demandeur = DemandeurResourceIT.createUpdatedEntity(em);
            em.persist(demandeur);
            em.flush();
        } else {
            demandeur = TestUtil.findAll(em, Demandeur.class).get(0);
        }
        demiFlux.setDemandeur(demandeur);
        return demiFlux;
    }

    @BeforeEach
    public void initTest() {
        demiFlux = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemiFlux() throws Exception {
        int databaseSizeBeforeCreate = demiFluxRepository.findAll().size();

        // Create the DemiFlux
        restDemiFluxMockMvc.perform(post("/api/demi-fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demiFlux)))
            .andExpect(status().isCreated());

        // Validate the DemiFlux in the database
        List<DemiFlux> demiFluxList = demiFluxRepository.findAll();
        assertThat(demiFluxList).hasSize(databaseSizeBeforeCreate + 1);
        DemiFlux testDemiFlux = demiFluxList.get(demiFluxList.size() - 1);
        assertThat(testDemiFlux.getAppliname()).isEqualTo(DEFAULT_APPLINAME);
        assertThat(testDemiFlux.getPartenairelocal()).isEqualTo(DEFAULT_PARTENAIRELOCAL);
        assertThat(testDemiFlux.getPartenairedistant()).isEqualTo(DEFAULT_PARTENAIREDISTANT);
        assertThat(testDemiFlux.getDirectory()).isEqualTo(DEFAULT_DIRECTORY);
        assertThat(testDemiFlux.getFilename()).isEqualTo(DEFAULT_FILENAME);
        assertThat(testDemiFlux.getMode()).isEqualTo(DEFAULT_MODE);
        assertThat(testDemiFlux.getTypology()).isEqualTo(DEFAULT_TYPOLOGY);
        assertThat(testDemiFlux.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDemiFlux.getHostname()).isEqualTo(DEFAULT_HOSTNAME);
        assertThat(testDemiFlux.getPort()).isEqualTo(DEFAULT_PORT);
    }

    @Test
    @Transactional
    public void createDemiFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demiFluxRepository.findAll().size();

        // Create the DemiFlux with an existing ID
        demiFlux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemiFluxMockMvc.perform(post("/api/demi-fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demiFlux)))
            .andExpect(status().isBadRequest());

        // Validate the DemiFlux in the database
        List<DemiFlux> demiFluxList = demiFluxRepository.findAll();
        assertThat(demiFluxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDemiFluxes() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demiFlux.getId().intValue())))
            .andExpect(jsonPath("$.[*].appliname").value(hasItem(DEFAULT_APPLINAME)))
            .andExpect(jsonPath("$.[*].partenairelocal").value(hasItem(DEFAULT_PARTENAIRELOCAL.toString())))
            .andExpect(jsonPath("$.[*].partenairedistant").value(hasItem(DEFAULT_PARTENAIREDISTANT)))
            .andExpect(jsonPath("$.[*].directory").value(hasItem(DEFAULT_DIRECTORY)))
            .andExpect(jsonPath("$.[*].filename").value(hasItem(DEFAULT_FILENAME)))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE.toString())))
            .andExpect(jsonPath("$.[*].typology").value(hasItem(DEFAULT_TYPOLOGY.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].hostname").value(hasItem(DEFAULT_HOSTNAME)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)));
    }
    
    @Test
    @Transactional
    public void getDemiFlux() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get the demiFlux
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes/{id}", demiFlux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demiFlux.getId().intValue()))
            .andExpect(jsonPath("$.appliname").value(DEFAULT_APPLINAME))
            .andExpect(jsonPath("$.partenairelocal").value(DEFAULT_PARTENAIRELOCAL.toString()))
            .andExpect(jsonPath("$.partenairedistant").value(DEFAULT_PARTENAIREDISTANT))
            .andExpect(jsonPath("$.directory").value(DEFAULT_DIRECTORY))
            .andExpect(jsonPath("$.filename").value(DEFAULT_FILENAME))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE.toString()))
            .andExpect(jsonPath("$.typology").value(DEFAULT_TYPOLOGY.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.hostname").value(DEFAULT_HOSTNAME))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT));
    }


    @Test
    @Transactional
    public void getDemiFluxesByIdFiltering() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        Long id = demiFlux.getId();

        defaultDemiFluxShouldBeFound("id.equals=" + id);
        defaultDemiFluxShouldNotBeFound("id.notEquals=" + id);

        defaultDemiFluxShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDemiFluxShouldNotBeFound("id.greaterThan=" + id);

        defaultDemiFluxShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDemiFluxShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByApplinameIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where appliname equals to DEFAULT_APPLINAME
        defaultDemiFluxShouldBeFound("appliname.equals=" + DEFAULT_APPLINAME);

        // Get all the demiFluxList where appliname equals to UPDATED_APPLINAME
        defaultDemiFluxShouldNotBeFound("appliname.equals=" + UPDATED_APPLINAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByApplinameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where appliname not equals to DEFAULT_APPLINAME
        defaultDemiFluxShouldNotBeFound("appliname.notEquals=" + DEFAULT_APPLINAME);

        // Get all the demiFluxList where appliname not equals to UPDATED_APPLINAME
        defaultDemiFluxShouldBeFound("appliname.notEquals=" + UPDATED_APPLINAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByApplinameIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where appliname in DEFAULT_APPLINAME or UPDATED_APPLINAME
        defaultDemiFluxShouldBeFound("appliname.in=" + DEFAULT_APPLINAME + "," + UPDATED_APPLINAME);

        // Get all the demiFluxList where appliname equals to UPDATED_APPLINAME
        defaultDemiFluxShouldNotBeFound("appliname.in=" + UPDATED_APPLINAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByApplinameIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where appliname is not null
        defaultDemiFluxShouldBeFound("appliname.specified=true");

        // Get all the demiFluxList where appliname is null
        defaultDemiFluxShouldNotBeFound("appliname.specified=false");
    }
                @Test
    @Transactional
    public void getAllDemiFluxesByApplinameContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where appliname contains DEFAULT_APPLINAME
        defaultDemiFluxShouldBeFound("appliname.contains=" + DEFAULT_APPLINAME);

        // Get all the demiFluxList where appliname contains UPDATED_APPLINAME
        defaultDemiFluxShouldNotBeFound("appliname.contains=" + UPDATED_APPLINAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByApplinameNotContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where appliname does not contain DEFAULT_APPLINAME
        defaultDemiFluxShouldNotBeFound("appliname.doesNotContain=" + DEFAULT_APPLINAME);

        // Get all the demiFluxList where appliname does not contain UPDATED_APPLINAME
        defaultDemiFluxShouldBeFound("appliname.doesNotContain=" + UPDATED_APPLINAME);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairelocalIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairelocal equals to DEFAULT_PARTENAIRELOCAL
        defaultDemiFluxShouldBeFound("partenairelocal.equals=" + DEFAULT_PARTENAIRELOCAL);

        // Get all the demiFluxList where partenairelocal equals to UPDATED_PARTENAIRELOCAL
        defaultDemiFluxShouldNotBeFound("partenairelocal.equals=" + UPDATED_PARTENAIRELOCAL);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairelocalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairelocal not equals to DEFAULT_PARTENAIRELOCAL
        defaultDemiFluxShouldNotBeFound("partenairelocal.notEquals=" + DEFAULT_PARTENAIRELOCAL);

        // Get all the demiFluxList where partenairelocal not equals to UPDATED_PARTENAIRELOCAL
        defaultDemiFluxShouldBeFound("partenairelocal.notEquals=" + UPDATED_PARTENAIRELOCAL);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairelocalIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairelocal in DEFAULT_PARTENAIRELOCAL or UPDATED_PARTENAIRELOCAL
        defaultDemiFluxShouldBeFound("partenairelocal.in=" + DEFAULT_PARTENAIRELOCAL + "," + UPDATED_PARTENAIRELOCAL);

        // Get all the demiFluxList where partenairelocal equals to UPDATED_PARTENAIRELOCAL
        defaultDemiFluxShouldNotBeFound("partenairelocal.in=" + UPDATED_PARTENAIRELOCAL);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairelocalIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairelocal is not null
        defaultDemiFluxShouldBeFound("partenairelocal.specified=true");

        // Get all the demiFluxList where partenairelocal is null
        defaultDemiFluxShouldNotBeFound("partenairelocal.specified=false");
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairedistantIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairedistant equals to DEFAULT_PARTENAIREDISTANT
        defaultDemiFluxShouldBeFound("partenairedistant.equals=" + DEFAULT_PARTENAIREDISTANT);

        // Get all the demiFluxList where partenairedistant equals to UPDATED_PARTENAIREDISTANT
        defaultDemiFluxShouldNotBeFound("partenairedistant.equals=" + UPDATED_PARTENAIREDISTANT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairedistantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairedistant not equals to DEFAULT_PARTENAIREDISTANT
        defaultDemiFluxShouldNotBeFound("partenairedistant.notEquals=" + DEFAULT_PARTENAIREDISTANT);

        // Get all the demiFluxList where partenairedistant not equals to UPDATED_PARTENAIREDISTANT
        defaultDemiFluxShouldBeFound("partenairedistant.notEquals=" + UPDATED_PARTENAIREDISTANT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairedistantIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairedistant in DEFAULT_PARTENAIREDISTANT or UPDATED_PARTENAIREDISTANT
        defaultDemiFluxShouldBeFound("partenairedistant.in=" + DEFAULT_PARTENAIREDISTANT + "," + UPDATED_PARTENAIREDISTANT);

        // Get all the demiFluxList where partenairedistant equals to UPDATED_PARTENAIREDISTANT
        defaultDemiFluxShouldNotBeFound("partenairedistant.in=" + UPDATED_PARTENAIREDISTANT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairedistantIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairedistant is not null
        defaultDemiFluxShouldBeFound("partenairedistant.specified=true");

        // Get all the demiFluxList where partenairedistant is null
        defaultDemiFluxShouldNotBeFound("partenairedistant.specified=false");
    }
                @Test
    @Transactional
    public void getAllDemiFluxesByPartenairedistantContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairedistant contains DEFAULT_PARTENAIREDISTANT
        defaultDemiFluxShouldBeFound("partenairedistant.contains=" + DEFAULT_PARTENAIREDISTANT);

        // Get all the demiFluxList where partenairedistant contains UPDATED_PARTENAIREDISTANT
        defaultDemiFluxShouldNotBeFound("partenairedistant.contains=" + UPDATED_PARTENAIREDISTANT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPartenairedistantNotContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where partenairedistant does not contain DEFAULT_PARTENAIREDISTANT
        defaultDemiFluxShouldNotBeFound("partenairedistant.doesNotContain=" + DEFAULT_PARTENAIREDISTANT);

        // Get all the demiFluxList where partenairedistant does not contain UPDATED_PARTENAIREDISTANT
        defaultDemiFluxShouldBeFound("partenairedistant.doesNotContain=" + UPDATED_PARTENAIREDISTANT);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByDirectoryIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where directory equals to DEFAULT_DIRECTORY
        defaultDemiFluxShouldBeFound("directory.equals=" + DEFAULT_DIRECTORY);

        // Get all the demiFluxList where directory equals to UPDATED_DIRECTORY
        defaultDemiFluxShouldNotBeFound("directory.equals=" + UPDATED_DIRECTORY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByDirectoryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where directory not equals to DEFAULT_DIRECTORY
        defaultDemiFluxShouldNotBeFound("directory.notEquals=" + DEFAULT_DIRECTORY);

        // Get all the demiFluxList where directory not equals to UPDATED_DIRECTORY
        defaultDemiFluxShouldBeFound("directory.notEquals=" + UPDATED_DIRECTORY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByDirectoryIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where directory in DEFAULT_DIRECTORY or UPDATED_DIRECTORY
        defaultDemiFluxShouldBeFound("directory.in=" + DEFAULT_DIRECTORY + "," + UPDATED_DIRECTORY);

        // Get all the demiFluxList where directory equals to UPDATED_DIRECTORY
        defaultDemiFluxShouldNotBeFound("directory.in=" + UPDATED_DIRECTORY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByDirectoryIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where directory is not null
        defaultDemiFluxShouldBeFound("directory.specified=true");

        // Get all the demiFluxList where directory is null
        defaultDemiFluxShouldNotBeFound("directory.specified=false");
    }
                @Test
    @Transactional
    public void getAllDemiFluxesByDirectoryContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where directory contains DEFAULT_DIRECTORY
        defaultDemiFluxShouldBeFound("directory.contains=" + DEFAULT_DIRECTORY);

        // Get all the demiFluxList where directory contains UPDATED_DIRECTORY
        defaultDemiFluxShouldNotBeFound("directory.contains=" + UPDATED_DIRECTORY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByDirectoryNotContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where directory does not contain DEFAULT_DIRECTORY
        defaultDemiFluxShouldNotBeFound("directory.doesNotContain=" + DEFAULT_DIRECTORY);

        // Get all the demiFluxList where directory does not contain UPDATED_DIRECTORY
        defaultDemiFluxShouldBeFound("directory.doesNotContain=" + UPDATED_DIRECTORY);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByFilenameIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where filename equals to DEFAULT_FILENAME
        defaultDemiFluxShouldBeFound("filename.equals=" + DEFAULT_FILENAME);

        // Get all the demiFluxList where filename equals to UPDATED_FILENAME
        defaultDemiFluxShouldNotBeFound("filename.equals=" + UPDATED_FILENAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByFilenameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where filename not equals to DEFAULT_FILENAME
        defaultDemiFluxShouldNotBeFound("filename.notEquals=" + DEFAULT_FILENAME);

        // Get all the demiFluxList where filename not equals to UPDATED_FILENAME
        defaultDemiFluxShouldBeFound("filename.notEquals=" + UPDATED_FILENAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByFilenameIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where filename in DEFAULT_FILENAME or UPDATED_FILENAME
        defaultDemiFluxShouldBeFound("filename.in=" + DEFAULT_FILENAME + "," + UPDATED_FILENAME);

        // Get all the demiFluxList where filename equals to UPDATED_FILENAME
        defaultDemiFluxShouldNotBeFound("filename.in=" + UPDATED_FILENAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByFilenameIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where filename is not null
        defaultDemiFluxShouldBeFound("filename.specified=true");

        // Get all the demiFluxList where filename is null
        defaultDemiFluxShouldNotBeFound("filename.specified=false");
    }
                @Test
    @Transactional
    public void getAllDemiFluxesByFilenameContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where filename contains DEFAULT_FILENAME
        defaultDemiFluxShouldBeFound("filename.contains=" + DEFAULT_FILENAME);

        // Get all the demiFluxList where filename contains UPDATED_FILENAME
        defaultDemiFluxShouldNotBeFound("filename.contains=" + UPDATED_FILENAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByFilenameNotContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where filename does not contain DEFAULT_FILENAME
        defaultDemiFluxShouldNotBeFound("filename.doesNotContain=" + DEFAULT_FILENAME);

        // Get all the demiFluxList where filename does not contain UPDATED_FILENAME
        defaultDemiFluxShouldBeFound("filename.doesNotContain=" + UPDATED_FILENAME);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByModeIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where mode equals to DEFAULT_MODE
        defaultDemiFluxShouldBeFound("mode.equals=" + DEFAULT_MODE);

        // Get all the demiFluxList where mode equals to UPDATED_MODE
        defaultDemiFluxShouldNotBeFound("mode.equals=" + UPDATED_MODE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByModeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where mode not equals to DEFAULT_MODE
        defaultDemiFluxShouldNotBeFound("mode.notEquals=" + DEFAULT_MODE);

        // Get all the demiFluxList where mode not equals to UPDATED_MODE
        defaultDemiFluxShouldBeFound("mode.notEquals=" + UPDATED_MODE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByModeIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where mode in DEFAULT_MODE or UPDATED_MODE
        defaultDemiFluxShouldBeFound("mode.in=" + DEFAULT_MODE + "," + UPDATED_MODE);

        // Get all the demiFluxList where mode equals to UPDATED_MODE
        defaultDemiFluxShouldNotBeFound("mode.in=" + UPDATED_MODE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByModeIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where mode is not null
        defaultDemiFluxShouldBeFound("mode.specified=true");

        // Get all the demiFluxList where mode is null
        defaultDemiFluxShouldNotBeFound("mode.specified=false");
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypologyIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where typology equals to DEFAULT_TYPOLOGY
        defaultDemiFluxShouldBeFound("typology.equals=" + DEFAULT_TYPOLOGY);

        // Get all the demiFluxList where typology equals to UPDATED_TYPOLOGY
        defaultDemiFluxShouldNotBeFound("typology.equals=" + UPDATED_TYPOLOGY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypologyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where typology not equals to DEFAULT_TYPOLOGY
        defaultDemiFluxShouldNotBeFound("typology.notEquals=" + DEFAULT_TYPOLOGY);

        // Get all the demiFluxList where typology not equals to UPDATED_TYPOLOGY
        defaultDemiFluxShouldBeFound("typology.notEquals=" + UPDATED_TYPOLOGY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypologyIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where typology in DEFAULT_TYPOLOGY or UPDATED_TYPOLOGY
        defaultDemiFluxShouldBeFound("typology.in=" + DEFAULT_TYPOLOGY + "," + UPDATED_TYPOLOGY);

        // Get all the demiFluxList where typology equals to UPDATED_TYPOLOGY
        defaultDemiFluxShouldNotBeFound("typology.in=" + UPDATED_TYPOLOGY);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypologyIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where typology is not null
        defaultDemiFluxShouldBeFound("typology.specified=true");

        // Get all the demiFluxList where typology is null
        defaultDemiFluxShouldNotBeFound("typology.specified=false");
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where type equals to DEFAULT_TYPE
        defaultDemiFluxShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the demiFluxList where type equals to UPDATED_TYPE
        defaultDemiFluxShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where type not equals to DEFAULT_TYPE
        defaultDemiFluxShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

        // Get all the demiFluxList where type not equals to UPDATED_TYPE
        defaultDemiFluxShouldBeFound("type.notEquals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultDemiFluxShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the demiFluxList where type equals to UPDATED_TYPE
        defaultDemiFluxShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where type is not null
        defaultDemiFluxShouldBeFound("type.specified=true");

        // Get all the demiFluxList where type is null
        defaultDemiFluxShouldNotBeFound("type.specified=false");
    }
                @Test
    @Transactional
    public void getAllDemiFluxesByTypeContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where type contains DEFAULT_TYPE
        defaultDemiFluxShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the demiFluxList where type contains UPDATED_TYPE
        defaultDemiFluxShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where type does not contain DEFAULT_TYPE
        defaultDemiFluxShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the demiFluxList where type does not contain UPDATED_TYPE
        defaultDemiFluxShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByHostnameIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where hostname equals to DEFAULT_HOSTNAME
        defaultDemiFluxShouldBeFound("hostname.equals=" + DEFAULT_HOSTNAME);

        // Get all the demiFluxList where hostname equals to UPDATED_HOSTNAME
        defaultDemiFluxShouldNotBeFound("hostname.equals=" + UPDATED_HOSTNAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByHostnameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where hostname not equals to DEFAULT_HOSTNAME
        defaultDemiFluxShouldNotBeFound("hostname.notEquals=" + DEFAULT_HOSTNAME);

        // Get all the demiFluxList where hostname not equals to UPDATED_HOSTNAME
        defaultDemiFluxShouldBeFound("hostname.notEquals=" + UPDATED_HOSTNAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByHostnameIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where hostname in DEFAULT_HOSTNAME or UPDATED_HOSTNAME
        defaultDemiFluxShouldBeFound("hostname.in=" + DEFAULT_HOSTNAME + "," + UPDATED_HOSTNAME);

        // Get all the demiFluxList where hostname equals to UPDATED_HOSTNAME
        defaultDemiFluxShouldNotBeFound("hostname.in=" + UPDATED_HOSTNAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByHostnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where hostname is not null
        defaultDemiFluxShouldBeFound("hostname.specified=true");

        // Get all the demiFluxList where hostname is null
        defaultDemiFluxShouldNotBeFound("hostname.specified=false");
    }
                @Test
    @Transactional
    public void getAllDemiFluxesByHostnameContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where hostname contains DEFAULT_HOSTNAME
        defaultDemiFluxShouldBeFound("hostname.contains=" + DEFAULT_HOSTNAME);

        // Get all the demiFluxList where hostname contains UPDATED_HOSTNAME
        defaultDemiFluxShouldNotBeFound("hostname.contains=" + UPDATED_HOSTNAME);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByHostnameNotContainsSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where hostname does not contain DEFAULT_HOSTNAME
        defaultDemiFluxShouldNotBeFound("hostname.doesNotContain=" + DEFAULT_HOSTNAME);

        // Get all the demiFluxList where hostname does not contain UPDATED_HOSTNAME
        defaultDemiFluxShouldBeFound("hostname.doesNotContain=" + UPDATED_HOSTNAME);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port equals to DEFAULT_PORT
        defaultDemiFluxShouldBeFound("port.equals=" + DEFAULT_PORT);

        // Get all the demiFluxList where port equals to UPDATED_PORT
        defaultDemiFluxShouldNotBeFound("port.equals=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsNotEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port not equals to DEFAULT_PORT
        defaultDemiFluxShouldNotBeFound("port.notEquals=" + DEFAULT_PORT);

        // Get all the demiFluxList where port not equals to UPDATED_PORT
        defaultDemiFluxShouldBeFound("port.notEquals=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsInShouldWork() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port in DEFAULT_PORT or UPDATED_PORT
        defaultDemiFluxShouldBeFound("port.in=" + DEFAULT_PORT + "," + UPDATED_PORT);

        // Get all the demiFluxList where port equals to UPDATED_PORT
        defaultDemiFluxShouldNotBeFound("port.in=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsNullOrNotNull() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port is not null
        defaultDemiFluxShouldBeFound("port.specified=true");

        // Get all the demiFluxList where port is null
        defaultDemiFluxShouldNotBeFound("port.specified=false");
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port is greater than or equal to DEFAULT_PORT
        defaultDemiFluxShouldBeFound("port.greaterThanOrEqual=" + DEFAULT_PORT);

        // Get all the demiFluxList where port is greater than or equal to UPDATED_PORT
        defaultDemiFluxShouldNotBeFound("port.greaterThanOrEqual=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port is less than or equal to DEFAULT_PORT
        defaultDemiFluxShouldBeFound("port.lessThanOrEqual=" + DEFAULT_PORT);

        // Get all the demiFluxList where port is less than or equal to SMALLER_PORT
        defaultDemiFluxShouldNotBeFound("port.lessThanOrEqual=" + SMALLER_PORT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsLessThanSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port is less than DEFAULT_PORT
        defaultDemiFluxShouldNotBeFound("port.lessThan=" + DEFAULT_PORT);

        // Get all the demiFluxList where port is less than UPDATED_PORT
        defaultDemiFluxShouldBeFound("port.lessThan=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    public void getAllDemiFluxesByPortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);

        // Get all the demiFluxList where port is greater than DEFAULT_PORT
        defaultDemiFluxShouldNotBeFound("port.greaterThan=" + DEFAULT_PORT);

        // Get all the demiFluxList where port is greater than SMALLER_PORT
        defaultDemiFluxShouldBeFound("port.greaterThan=" + SMALLER_PORT);
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByContactProjetIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);
        ContactProjet contactProjet = ContactProjetResourceIT.createEntity(em);
        em.persist(contactProjet);
        em.flush();
        demiFlux.setContactProjet(contactProjet);
        demiFluxRepository.saveAndFlush(demiFlux);
        Long contactProjetId = contactProjet.getId();

        // Get all the demiFluxList where contactProjet equals to contactProjetId
        defaultDemiFluxShouldBeFound("contactProjetId.equals=" + contactProjetId);

        // Get all the demiFluxList where contactProjet equals to contactProjetId + 1
        defaultDemiFluxShouldNotBeFound("contactProjetId.equals=" + (contactProjetId + 1));
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByContactTechniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);
        ContactTechnique contactTechnique = ContactTechniqueResourceIT.createEntity(em);
        em.persist(contactTechnique);
        em.flush();
        demiFlux.setContactTechnique(contactTechnique);
        demiFluxRepository.saveAndFlush(demiFlux);
        Long contactTechniqueId = contactTechnique.getId();

        // Get all the demiFluxList where contactTechnique equals to contactTechniqueId
        defaultDemiFluxShouldBeFound("contactTechniqueId.equals=" + contactTechniqueId);

        // Get all the demiFluxList where contactTechnique equals to contactTechniqueId + 1
        defaultDemiFluxShouldNotBeFound("contactTechniqueId.equals=" + (contactTechniqueId + 1));
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByPESITIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);
        PESIT pESIT = PESITResourceIT.createEntity(em);
        em.persist(pESIT);
        em.flush();
        demiFlux.setPESIT(pESIT);
        demiFluxRepository.saveAndFlush(demiFlux);
        Long pESITId = pESIT.getId();

        // Get all the demiFluxList where pESIT equals to pESITId
        defaultDemiFluxShouldBeFound("pESITId.equals=" + pESITId);

        // Get all the demiFluxList where pESIT equals to pESITId + 1
        defaultDemiFluxShouldNotBeFound("pESITId.equals=" + (pESITId + 1));
    }


    @Test
    @Transactional
    public void getAllDemiFluxesBySFTPIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);
        SFTP sFTP = SFTPResourceIT.createEntity(em);
        em.persist(sFTP);
        em.flush();
        demiFlux.setSFTP(sFTP);
        demiFluxRepository.saveAndFlush(demiFlux);
        Long sFTPId = sFTP.getId();

        // Get all the demiFluxList where sFTP equals to sFTPId
        defaultDemiFluxShouldBeFound("sFTPId.equals=" + sFTPId);

        // Get all the demiFluxList where sFTP equals to sFTPId + 1
        defaultDemiFluxShouldNotBeFound("sFTPId.equals=" + (sFTPId + 1));
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByRoutageIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);
        ROUTAGE routage = ROUTAGEResourceIT.createEntity(em);
        em.persist(routage);
        em.flush();
        demiFlux.addRoutage(routage);
        demiFluxRepository.saveAndFlush(demiFlux);
        Long routageId = routage.getId();

        // Get all the demiFluxList where routage equals to routageId
        defaultDemiFluxShouldBeFound("routageId.equals=" + routageId);

        // Get all the demiFluxList where routage equals to routageId + 1
        defaultDemiFluxShouldNotBeFound("routageId.equals=" + (routageId + 1));
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByDemandeurIsEqualToSomething() throws Exception {
        // Get already existing entity
        Demandeur demandeur = demiFlux.getDemandeur();
        demiFluxRepository.saveAndFlush(demiFlux);
        Long demandeurId = demandeur.getId();

        // Get all the demiFluxList where demandeur equals to demandeurId
        defaultDemiFluxShouldBeFound("demandeurId.equals=" + demandeurId);

        // Get all the demiFluxList where demandeur equals to demandeurId + 1
        defaultDemiFluxShouldNotBeFound("demandeurId.equals=" + (demandeurId + 1));
    }


    @Test
    @Transactional
    public void getAllDemiFluxesByFluxIsEqualToSomething() throws Exception {
        // Initialize the database
        demiFluxRepository.saveAndFlush(demiFlux);
        Flux flux = FluxResourceIT.createEntity(em);
        em.persist(flux);
        em.flush();
        demiFlux.setFlux(flux);
        demiFluxRepository.saveAndFlush(demiFlux);
        Long fluxId = flux.getId();

        // Get all the demiFluxList where flux equals to fluxId
        defaultDemiFluxShouldBeFound("fluxId.equals=" + fluxId);

        // Get all the demiFluxList where flux equals to fluxId + 1
        defaultDemiFluxShouldNotBeFound("fluxId.equals=" + (fluxId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDemiFluxShouldBeFound(String filter) throws Exception {
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demiFlux.getId().intValue())))
            .andExpect(jsonPath("$.[*].appliname").value(hasItem(DEFAULT_APPLINAME)))
            .andExpect(jsonPath("$.[*].partenairelocal").value(hasItem(DEFAULT_PARTENAIRELOCAL.toString())))
            .andExpect(jsonPath("$.[*].partenairedistant").value(hasItem(DEFAULT_PARTENAIREDISTANT)))
            .andExpect(jsonPath("$.[*].directory").value(hasItem(DEFAULT_DIRECTORY)))
            .andExpect(jsonPath("$.[*].filename").value(hasItem(DEFAULT_FILENAME)))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE.toString())))
            .andExpect(jsonPath("$.[*].typology").value(hasItem(DEFAULT_TYPOLOGY.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].hostname").value(hasItem(DEFAULT_HOSTNAME)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)));

        // Check, that the count call also returns 1
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDemiFluxShouldNotBeFound(String filter) throws Exception {
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDemiFlux() throws Exception {
        // Get the demiFlux
        restDemiFluxMockMvc.perform(get("/api/demi-fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemiFlux() throws Exception {
        // Initialize the database
        demiFluxService.save(demiFlux);

        int databaseSizeBeforeUpdate = demiFluxRepository.findAll().size();

        // Update the demiFlux
        DemiFlux updatedDemiFlux = demiFluxRepository.findById(demiFlux.getId()).get();
        // Disconnect from session so that the updates on updatedDemiFlux are not directly saved in db
        em.detach(updatedDemiFlux);
        updatedDemiFlux
            .appliname(UPDATED_APPLINAME)
            .partenairelocal(UPDATED_PARTENAIRELOCAL)
            .partenairedistant(UPDATED_PARTENAIREDISTANT)
            .directory(UPDATED_DIRECTORY)
            .filename(UPDATED_FILENAME)
            .mode(UPDATED_MODE)
            .typology(UPDATED_TYPOLOGY)
            .type(UPDATED_TYPE)
            .hostname(UPDATED_HOSTNAME)
            .port(UPDATED_PORT);

        restDemiFluxMockMvc.perform(put("/api/demi-fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemiFlux)))
            .andExpect(status().isOk());

        // Validate the DemiFlux in the database
        List<DemiFlux> demiFluxList = demiFluxRepository.findAll();
        assertThat(demiFluxList).hasSize(databaseSizeBeforeUpdate);
        DemiFlux testDemiFlux = demiFluxList.get(demiFluxList.size() - 1);
        assertThat(testDemiFlux.getAppliname()).isEqualTo(UPDATED_APPLINAME);
        assertThat(testDemiFlux.getPartenairelocal()).isEqualTo(UPDATED_PARTENAIRELOCAL);
        assertThat(testDemiFlux.getPartenairedistant()).isEqualTo(UPDATED_PARTENAIREDISTANT);
        assertThat(testDemiFlux.getDirectory()).isEqualTo(UPDATED_DIRECTORY);
        assertThat(testDemiFlux.getFilename()).isEqualTo(UPDATED_FILENAME);
        assertThat(testDemiFlux.getMode()).isEqualTo(UPDATED_MODE);
        assertThat(testDemiFlux.getTypology()).isEqualTo(UPDATED_TYPOLOGY);
        assertThat(testDemiFlux.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDemiFlux.getHostname()).isEqualTo(UPDATED_HOSTNAME);
        assertThat(testDemiFlux.getPort()).isEqualTo(UPDATED_PORT);
    }

    @Test
    @Transactional
    public void updateNonExistingDemiFlux() throws Exception {
        int databaseSizeBeforeUpdate = demiFluxRepository.findAll().size();

        // Create the DemiFlux

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemiFluxMockMvc.perform(put("/api/demi-fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demiFlux)))
            .andExpect(status().isBadRequest());

        // Validate the DemiFlux in the database
        List<DemiFlux> demiFluxList = demiFluxRepository.findAll();
        assertThat(demiFluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemiFlux() throws Exception {
        // Initialize the database
        demiFluxService.save(demiFlux);

        int databaseSizeBeforeDelete = demiFluxRepository.findAll().size();

        // Delete the demiFlux
        restDemiFluxMockMvc.perform(delete("/api/demi-fluxes/{id}", demiFlux.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DemiFlux> demiFluxList = demiFluxRepository.findAll();
        assertThat(demiFluxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
