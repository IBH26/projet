package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.ROUTAGE;
import com.mycompany.myapp.repository.ROUTAGERepository;
import com.mycompany.myapp.service.ROUTAGEService;
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
 * Integration tests for the {@link ROUTAGEResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class ROUTAGEResourceIT {

    private static final String DEFAULT_DR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DR_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RENAME = false;
    private static final Boolean UPDATED_RENAME = true;

    private static final String DEFAULT_MASK_FILE = "AAAAAAAAAA";
    private static final String UPDATED_MASK_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTORY = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTORY = "BBBBBBBBBB";

    private static final String DEFAULT_HANDLINGTYPE = "AAAAAAAAAA";
    private static final String UPDATED_HANDLINGTYPE = "BBBBBBBBBB";

    @Autowired
    private ROUTAGERepository rOUTAGERepository;

    @Autowired
    private ROUTAGEService rOUTAGEService;

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

    private MockMvc restROUTAGEMockMvc;

    private ROUTAGE rOUTAGE;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ROUTAGEResource rOUTAGEResource = new ROUTAGEResource(rOUTAGEService);
        this.restROUTAGEMockMvc = MockMvcBuilders.standaloneSetup(rOUTAGEResource)
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
    public static ROUTAGE createEntity(EntityManager em) {
        ROUTAGE rOUTAGE = new ROUTAGE()
            .drName(DEFAULT_DR_NAME)
            .rename(DEFAULT_RENAME)
            .maskFile(DEFAULT_MASK_FILE)
            .directory(DEFAULT_DIRECTORY)
            .handlingtype(DEFAULT_HANDLINGTYPE);
        return rOUTAGE;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ROUTAGE createUpdatedEntity(EntityManager em) {
        ROUTAGE rOUTAGE = new ROUTAGE()
            .drName(UPDATED_DR_NAME)
            .rename(UPDATED_RENAME)
            .maskFile(UPDATED_MASK_FILE)
            .directory(UPDATED_DIRECTORY)
            .handlingtype(UPDATED_HANDLINGTYPE);
        return rOUTAGE;
    }

    @BeforeEach
    public void initTest() {
        rOUTAGE = createEntity(em);
    }

    @Test
    @Transactional
    public void createROUTAGE() throws Exception {
        int databaseSizeBeforeCreate = rOUTAGERepository.findAll().size();

        // Create the ROUTAGE
        restROUTAGEMockMvc.perform(post("/api/routages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rOUTAGE)))
            .andExpect(status().isCreated());

        // Validate the ROUTAGE in the database
        List<ROUTAGE> rOUTAGEList = rOUTAGERepository.findAll();
        assertThat(rOUTAGEList).hasSize(databaseSizeBeforeCreate + 1);
        ROUTAGE testROUTAGE = rOUTAGEList.get(rOUTAGEList.size() - 1);
        assertThat(testROUTAGE.getDrName()).isEqualTo(DEFAULT_DR_NAME);
        assertThat(testROUTAGE.isRename()).isEqualTo(DEFAULT_RENAME);
        assertThat(testROUTAGE.getMaskFile()).isEqualTo(DEFAULT_MASK_FILE);
        assertThat(testROUTAGE.getDirectory()).isEqualTo(DEFAULT_DIRECTORY);
        assertThat(testROUTAGE.getHandlingtype()).isEqualTo(DEFAULT_HANDLINGTYPE);
    }

    @Test
    @Transactional
    public void createROUTAGEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rOUTAGERepository.findAll().size();

        // Create the ROUTAGE with an existing ID
        rOUTAGE.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restROUTAGEMockMvc.perform(post("/api/routages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rOUTAGE)))
            .andExpect(status().isBadRequest());

        // Validate the ROUTAGE in the database
        List<ROUTAGE> rOUTAGEList = rOUTAGERepository.findAll();
        assertThat(rOUTAGEList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDrNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rOUTAGERepository.findAll().size();
        // set the field null
        rOUTAGE.setDrName(null);

        // Create the ROUTAGE, which fails.

        restROUTAGEMockMvc.perform(post("/api/routages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rOUTAGE)))
            .andExpect(status().isBadRequest());

        List<ROUTAGE> rOUTAGEList = rOUTAGERepository.findAll();
        assertThat(rOUTAGEList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllROUTAGES() throws Exception {
        // Initialize the database
        rOUTAGERepository.saveAndFlush(rOUTAGE);

        // Get all the rOUTAGEList
        restROUTAGEMockMvc.perform(get("/api/routages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rOUTAGE.getId().intValue())))
            .andExpect(jsonPath("$.[*].drName").value(hasItem(DEFAULT_DR_NAME)))
            .andExpect(jsonPath("$.[*].rename").value(hasItem(DEFAULT_RENAME.booleanValue())))
            .andExpect(jsonPath("$.[*].maskFile").value(hasItem(DEFAULT_MASK_FILE)))
            .andExpect(jsonPath("$.[*].directory").value(hasItem(DEFAULT_DIRECTORY)))
            .andExpect(jsonPath("$.[*].handlingtype").value(hasItem(DEFAULT_HANDLINGTYPE)));
    }
    
    @Test
    @Transactional
    public void getROUTAGE() throws Exception {
        // Initialize the database
        rOUTAGERepository.saveAndFlush(rOUTAGE);

        // Get the rOUTAGE
        restROUTAGEMockMvc.perform(get("/api/routages/{id}", rOUTAGE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rOUTAGE.getId().intValue()))
            .andExpect(jsonPath("$.drName").value(DEFAULT_DR_NAME))
            .andExpect(jsonPath("$.rename").value(DEFAULT_RENAME.booleanValue()))
            .andExpect(jsonPath("$.maskFile").value(DEFAULT_MASK_FILE))
            .andExpect(jsonPath("$.directory").value(DEFAULT_DIRECTORY))
            .andExpect(jsonPath("$.handlingtype").value(DEFAULT_HANDLINGTYPE));
    }

    @Test
    @Transactional
    public void getNonExistingROUTAGE() throws Exception {
        // Get the rOUTAGE
        restROUTAGEMockMvc.perform(get("/api/routages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateROUTAGE() throws Exception {
        // Initialize the database
        rOUTAGEService.save(rOUTAGE);

        int databaseSizeBeforeUpdate = rOUTAGERepository.findAll().size();

        // Update the rOUTAGE
        ROUTAGE updatedROUTAGE = rOUTAGERepository.findById(rOUTAGE.getId()).get();
        // Disconnect from session so that the updates on updatedROUTAGE are not directly saved in db
        em.detach(updatedROUTAGE);
        updatedROUTAGE
            .drName(UPDATED_DR_NAME)
            .rename(UPDATED_RENAME)
            .maskFile(UPDATED_MASK_FILE)
            .directory(UPDATED_DIRECTORY)
            .handlingtype(UPDATED_HANDLINGTYPE);

        restROUTAGEMockMvc.perform(put("/api/routages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedROUTAGE)))
            .andExpect(status().isOk());

        // Validate the ROUTAGE in the database
        List<ROUTAGE> rOUTAGEList = rOUTAGERepository.findAll();
        assertThat(rOUTAGEList).hasSize(databaseSizeBeforeUpdate);
        ROUTAGE testROUTAGE = rOUTAGEList.get(rOUTAGEList.size() - 1);
        assertThat(testROUTAGE.getDrName()).isEqualTo(UPDATED_DR_NAME);
        assertThat(testROUTAGE.isRename()).isEqualTo(UPDATED_RENAME);
        assertThat(testROUTAGE.getMaskFile()).isEqualTo(UPDATED_MASK_FILE);
        assertThat(testROUTAGE.getDirectory()).isEqualTo(UPDATED_DIRECTORY);
        assertThat(testROUTAGE.getHandlingtype()).isEqualTo(UPDATED_HANDLINGTYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingROUTAGE() throws Exception {
        int databaseSizeBeforeUpdate = rOUTAGERepository.findAll().size();

        // Create the ROUTAGE

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restROUTAGEMockMvc.perform(put("/api/routages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rOUTAGE)))
            .andExpect(status().isBadRequest());

        // Validate the ROUTAGE in the database
        List<ROUTAGE> rOUTAGEList = rOUTAGERepository.findAll();
        assertThat(rOUTAGEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteROUTAGE() throws Exception {
        // Initialize the database
        rOUTAGEService.save(rOUTAGE);

        int databaseSizeBeforeDelete = rOUTAGERepository.findAll().size();

        // Delete the rOUTAGE
        restROUTAGEMockMvc.perform(delete("/api/routages/{id}", rOUTAGE.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ROUTAGE> rOUTAGEList = rOUTAGERepository.findAll();
        assertThat(rOUTAGEList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
