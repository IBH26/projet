package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.PESIT;
import com.mycompany.myapp.repository.PESITRepository;
import com.mycompany.myapp.service.PESITService;
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
 * Integration tests for the {@link PESITResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class PESITResourceIT {

    private static final String DEFAULT_CODE_SITE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_APPLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSCODING = "AAAAAAAAAA";
    private static final String UPDATED_TRANSCODING = "BBBBBBBBBB";

    private static final String DEFAULT_COMPRESSION = "AAAAAAAAAA";
    private static final String UPDATED_COMPRESSION = "BBBBBBBBBB";

    @Autowired
    private PESITRepository pESITRepository;

    @Autowired
    private PESITService pESITService;

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

    private MockMvc restPESITMockMvc;

    private PESIT pESIT;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PESITResource pESITResource = new PESITResource(pESITService);
        this.restPESITMockMvc = MockMvcBuilders.standaloneSetup(pESITResource)
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
    public static PESIT createEntity(EntityManager em) {
        PESIT pESIT = new PESIT()
            .codeSite(DEFAULT_CODE_SITE)
            .codeApplication(DEFAULT_CODE_APPLICATION)
            .transcoding(DEFAULT_TRANSCODING)
            .compression(DEFAULT_COMPRESSION);
        return pESIT;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PESIT createUpdatedEntity(EntityManager em) {
        PESIT pESIT = new PESIT()
            .codeSite(UPDATED_CODE_SITE)
            .codeApplication(UPDATED_CODE_APPLICATION)
            .transcoding(UPDATED_TRANSCODING)
            .compression(UPDATED_COMPRESSION);
        return pESIT;
    }

    @BeforeEach
    public void initTest() {
        pESIT = createEntity(em);
    }

    @Test
    @Transactional
    public void createPESIT() throws Exception {
        int databaseSizeBeforeCreate = pESITRepository.findAll().size();

        // Create the PESIT
        restPESITMockMvc.perform(post("/api/pesits")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pESIT)))
            .andExpect(status().isCreated());

        // Validate the PESIT in the database
        List<PESIT> pESITList = pESITRepository.findAll();
        assertThat(pESITList).hasSize(databaseSizeBeforeCreate + 1);
        PESIT testPESIT = pESITList.get(pESITList.size() - 1);
        assertThat(testPESIT.getCodeSite()).isEqualTo(DEFAULT_CODE_SITE);
        assertThat(testPESIT.getCodeApplication()).isEqualTo(DEFAULT_CODE_APPLICATION);
        assertThat(testPESIT.getTranscoding()).isEqualTo(DEFAULT_TRANSCODING);
        assertThat(testPESIT.getCompression()).isEqualTo(DEFAULT_COMPRESSION);
    }

    @Test
    @Transactional
    public void createPESITWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pESITRepository.findAll().size();

        // Create the PESIT with an existing ID
        pESIT.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPESITMockMvc.perform(post("/api/pesits")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pESIT)))
            .andExpect(status().isBadRequest());

        // Validate the PESIT in the database
        List<PESIT> pESITList = pESITRepository.findAll();
        assertThat(pESITList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPESITS() throws Exception {
        // Initialize the database
        pESITRepository.saveAndFlush(pESIT);

        // Get all the pESITList
        restPESITMockMvc.perform(get("/api/pesits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pESIT.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeSite").value(hasItem(DEFAULT_CODE_SITE)))
            .andExpect(jsonPath("$.[*].codeApplication").value(hasItem(DEFAULT_CODE_APPLICATION)))
            .andExpect(jsonPath("$.[*].transcoding").value(hasItem(DEFAULT_TRANSCODING)))
            .andExpect(jsonPath("$.[*].compression").value(hasItem(DEFAULT_COMPRESSION)));
    }
    
    @Test
    @Transactional
    public void getPESIT() throws Exception {
        // Initialize the database
        pESITRepository.saveAndFlush(pESIT);

        // Get the pESIT
        restPESITMockMvc.perform(get("/api/pesits/{id}", pESIT.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pESIT.getId().intValue()))
            .andExpect(jsonPath("$.codeSite").value(DEFAULT_CODE_SITE))
            .andExpect(jsonPath("$.codeApplication").value(DEFAULT_CODE_APPLICATION))
            .andExpect(jsonPath("$.transcoding").value(DEFAULT_TRANSCODING))
            .andExpect(jsonPath("$.compression").value(DEFAULT_COMPRESSION));
    }

    @Test
    @Transactional
    public void getNonExistingPESIT() throws Exception {
        // Get the pESIT
        restPESITMockMvc.perform(get("/api/pesits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePESIT() throws Exception {
        // Initialize the database
        pESITService.save(pESIT);

        int databaseSizeBeforeUpdate = pESITRepository.findAll().size();

        // Update the pESIT
        PESIT updatedPESIT = pESITRepository.findById(pESIT.getId()).get();
        // Disconnect from session so that the updates on updatedPESIT are not directly saved in db
        em.detach(updatedPESIT);
        updatedPESIT
            .codeSite(UPDATED_CODE_SITE)
            .codeApplication(UPDATED_CODE_APPLICATION)
            .transcoding(UPDATED_TRANSCODING)
            .compression(UPDATED_COMPRESSION);

        restPESITMockMvc.perform(put("/api/pesits")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPESIT)))
            .andExpect(status().isOk());

        // Validate the PESIT in the database
        List<PESIT> pESITList = pESITRepository.findAll();
        assertThat(pESITList).hasSize(databaseSizeBeforeUpdate);
        PESIT testPESIT = pESITList.get(pESITList.size() - 1);
        assertThat(testPESIT.getCodeSite()).isEqualTo(UPDATED_CODE_SITE);
        assertThat(testPESIT.getCodeApplication()).isEqualTo(UPDATED_CODE_APPLICATION);
        assertThat(testPESIT.getTranscoding()).isEqualTo(UPDATED_TRANSCODING);
        assertThat(testPESIT.getCompression()).isEqualTo(UPDATED_COMPRESSION);
    }

    @Test
    @Transactional
    public void updateNonExistingPESIT() throws Exception {
        int databaseSizeBeforeUpdate = pESITRepository.findAll().size();

        // Create the PESIT

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPESITMockMvc.perform(put("/api/pesits")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pESIT)))
            .andExpect(status().isBadRequest());

        // Validate the PESIT in the database
        List<PESIT> pESITList = pESITRepository.findAll();
        assertThat(pESITList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePESIT() throws Exception {
        // Initialize the database
        pESITService.save(pESIT);

        int databaseSizeBeforeDelete = pESITRepository.findAll().size();

        // Delete the pESIT
        restPESITMockMvc.perform(delete("/api/pesits/{id}", pESIT.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PESIT> pESITList = pESITRepository.findAll();
        assertThat(pESITList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
