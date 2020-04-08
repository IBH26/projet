package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.Demandeur;
import com.mycompany.myapp.repository.DemandeurRepository;
import com.mycompany.myapp.service.DemandeurService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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

/**
 * Integration tests for the {@link DemandeurResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class DemandeurResourceIT {

    private static final String DEFAULT_NAME_D = "AAAAAAAAAA";
    private static final String UPDATED_NAME_D = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_D = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_D = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_D = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_D = "BBBBBBBBBB";

    @Autowired
    private DemandeurRepository demandeurRepository;

    @Autowired
    private DemandeurService demandeurService;

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

    private MockMvc restDemandeurMockMvc;

    private Demandeur demandeur;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DemandeurResource demandeurResource = new DemandeurResource(demandeurService);
        this.restDemandeurMockMvc = MockMvcBuilders.standaloneSetup(demandeurResource)
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
    public static Demandeur createEntity(EntityManager em) {
        Demandeur demandeur = new Demandeur()
            .nameD(DEFAULT_NAME_D)
            .functionD(DEFAULT_FUNCTION_D)
            .projectD(DEFAULT_PROJECT_D);
        return demandeur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demandeur createUpdatedEntity(EntityManager em) {
        Demandeur demandeur = new Demandeur()
            .nameD(UPDATED_NAME_D)
            .functionD(UPDATED_FUNCTION_D)
            .projectD(UPDATED_PROJECT_D);
        return demandeur;
    }

    @BeforeEach
    public void initTest() {
        demandeur = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemandeur() throws Exception {
        int databaseSizeBeforeCreate = demandeurRepository.findAll().size();

        // Create the Demandeur
        restDemandeurMockMvc.perform(post("/api/demandeurs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeur)))
            .andExpect(status().isCreated());

        // Validate the Demandeur in the database
        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeCreate + 1);
        Demandeur testDemandeur = demandeurList.get(demandeurList.size() - 1);
        assertThat(testDemandeur.getNameD()).isEqualTo(DEFAULT_NAME_D);
        assertThat(testDemandeur.getFunctionD()).isEqualTo(DEFAULT_FUNCTION_D);
        assertThat(testDemandeur.getProjectD()).isEqualTo(DEFAULT_PROJECT_D);
    }

    @Test
    @Transactional
    public void createDemandeurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeurRepository.findAll().size();

        // Create the Demandeur with an existing ID
        demandeur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeurMockMvc.perform(post("/api/demandeurs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeur)))
            .andExpect(status().isBadRequest());

        // Validate the Demandeur in the database
        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameDIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeurRepository.findAll().size();
        // set the field null
        demandeur.setNameD(null);

        // Create the Demandeur, which fails.

        restDemandeurMockMvc.perform(post("/api/demandeurs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeur)))
            .andExpect(status().isBadRequest());

        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFunctionDIsRequired() throws Exception {
        int databaseSizeBeforeTest = demandeurRepository.findAll().size();
        // set the field null
        demandeur.setFunctionD(null);

        // Create the Demandeur, which fails.

        restDemandeurMockMvc.perform(post("/api/demandeurs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeur)))
            .andExpect(status().isBadRequest());

        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDemandeurs() throws Exception {
        // Initialize the database
        demandeurRepository.saveAndFlush(demandeur);

        // Get all the demandeurList
        restDemandeurMockMvc.perform(get("/api/demandeurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameD").value(hasItem(DEFAULT_NAME_D)))
            .andExpect(jsonPath("$.[*].functionD").value(hasItem(DEFAULT_FUNCTION_D)))
            .andExpect(jsonPath("$.[*].projectD").value(hasItem(DEFAULT_PROJECT_D)));
    }
    
    @Test
    @Transactional
    public void getDemandeur() throws Exception {
        // Initialize the database
        demandeurRepository.saveAndFlush(demandeur);

        // Get the demandeur
        restDemandeurMockMvc.perform(get("/api/demandeurs/{id}", demandeur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demandeur.getId().intValue()))
            .andExpect(jsonPath("$.nameD").value(DEFAULT_NAME_D))
            .andExpect(jsonPath("$.functionD").value(DEFAULT_FUNCTION_D))
            .andExpect(jsonPath("$.projectD").value(DEFAULT_PROJECT_D));
    }

    @Test
    @Transactional
    public void getNonExistingDemandeur() throws Exception {
        // Get the demandeur
        restDemandeurMockMvc.perform(get("/api/demandeurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemandeur() throws Exception {
        // Initialize the database
        demandeurService.save(demandeur);

        int databaseSizeBeforeUpdate = demandeurRepository.findAll().size();

        // Update the demandeur
        Demandeur updatedDemandeur = demandeurRepository.findById(demandeur.getId()).get();
        // Disconnect from session so that the updates on updatedDemandeur are not directly saved in db
        em.detach(updatedDemandeur);
        updatedDemandeur
            .nameD(UPDATED_NAME_D)
            .functionD(UPDATED_FUNCTION_D)
            .projectD(UPDATED_PROJECT_D);

        restDemandeurMockMvc.perform(put("/api/demandeurs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemandeur)))
            .andExpect(status().isOk());

        // Validate the Demandeur in the database
        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeUpdate);
        Demandeur testDemandeur = demandeurList.get(demandeurList.size() - 1);
        assertThat(testDemandeur.getNameD()).isEqualTo(UPDATED_NAME_D);
        assertThat(testDemandeur.getFunctionD()).isEqualTo(UPDATED_FUNCTION_D);
        assertThat(testDemandeur.getProjectD()).isEqualTo(UPDATED_PROJECT_D);
    }

    @Test
    @Transactional
    public void updateNonExistingDemandeur() throws Exception {
        int databaseSizeBeforeUpdate = demandeurRepository.findAll().size();

        // Create the Demandeur

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeurMockMvc.perform(put("/api/demandeurs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(demandeur)))
            .andExpect(status().isBadRequest());

        // Validate the Demandeur in the database
        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemandeur() throws Exception {
        // Initialize the database
        demandeurService.save(demandeur);

        int databaseSizeBeforeDelete = demandeurRepository.findAll().size();

        // Delete the demandeur
        restDemandeurMockMvc.perform(delete("/api/demandeurs/{id}", demandeur.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Demandeur> demandeurList = demandeurRepository.findAll();
        assertThat(demandeurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
