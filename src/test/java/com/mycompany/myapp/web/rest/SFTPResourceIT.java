package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.SFTP;
import com.mycompany.myapp.repository.SFTPRepository;
import com.mycompany.myapp.service.SFTPService;
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
 * Integration tests for the {@link SFTPResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class SFTPResourceIT {

    private static final String DEFAULT_FORMAT_TRANSFER = "AAAAAAAAAA";
    private static final String UPDATED_FORMAT_TRANSFER = "BBBBBBBBBB";

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    @Autowired
    private SFTPRepository sFTPRepository;

    @Autowired
    private SFTPService sFTPService;

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

    private MockMvc restSFTPMockMvc;

    private SFTP sFTP;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SFTPResource sFTPResource = new SFTPResource(sFTPService);
        this.restSFTPMockMvc = MockMvcBuilders.standaloneSetup(sFTPResource)
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
    public static SFTP createEntity(EntityManager em) {
        SFTP sFTP = new SFTP()
            .formatTransfer(DEFAULT_FORMAT_TRANSFER)
            .user(DEFAULT_USER)
            .key(DEFAULT_KEY);
        return sFTP;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SFTP createUpdatedEntity(EntityManager em) {
        SFTP sFTP = new SFTP()
            .formatTransfer(UPDATED_FORMAT_TRANSFER)
            .user(UPDATED_USER)
            .key(UPDATED_KEY);
        return sFTP;
    }

    @BeforeEach
    public void initTest() {
        sFTP = createEntity(em);
    }

    @Test
    @Transactional
    public void createSFTP() throws Exception {
        int databaseSizeBeforeCreate = sFTPRepository.findAll().size();

        // Create the SFTP
        restSFTPMockMvc.perform(post("/api/sftps")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sFTP)))
            .andExpect(status().isCreated());

        // Validate the SFTP in the database
        List<SFTP> sFTPList = sFTPRepository.findAll();
        assertThat(sFTPList).hasSize(databaseSizeBeforeCreate + 1);
        SFTP testSFTP = sFTPList.get(sFTPList.size() - 1);
        assertThat(testSFTP.getFormatTransfer()).isEqualTo(DEFAULT_FORMAT_TRANSFER);
        assertThat(testSFTP.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testSFTP.getKey()).isEqualTo(DEFAULT_KEY);
    }

    @Test
    @Transactional
    public void createSFTPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sFTPRepository.findAll().size();

        // Create the SFTP with an existing ID
        sFTP.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSFTPMockMvc.perform(post("/api/sftps")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sFTP)))
            .andExpect(status().isBadRequest());

        // Validate the SFTP in the database
        List<SFTP> sFTPList = sFTPRepository.findAll();
        assertThat(sFTPList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSFTPS() throws Exception {
        // Initialize the database
        sFTPRepository.saveAndFlush(sFTP);

        // Get all the sFTPList
        restSFTPMockMvc.perform(get("/api/sftps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sFTP.getId().intValue())))
            .andExpect(jsonPath("$.[*].formatTransfer").value(hasItem(DEFAULT_FORMAT_TRANSFER)))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)));
    }
    
    @Test
    @Transactional
    public void getSFTP() throws Exception {
        // Initialize the database
        sFTPRepository.saveAndFlush(sFTP);

        // Get the sFTP
        restSFTPMockMvc.perform(get("/api/sftps/{id}", sFTP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sFTP.getId().intValue()))
            .andExpect(jsonPath("$.formatTransfer").value(DEFAULT_FORMAT_TRANSFER))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY));
    }

    @Test
    @Transactional
    public void getNonExistingSFTP() throws Exception {
        // Get the sFTP
        restSFTPMockMvc.perform(get("/api/sftps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSFTP() throws Exception {
        // Initialize the database
        sFTPService.save(sFTP);

        int databaseSizeBeforeUpdate = sFTPRepository.findAll().size();

        // Update the sFTP
        SFTP updatedSFTP = sFTPRepository.findById(sFTP.getId()).get();
        // Disconnect from session so that the updates on updatedSFTP are not directly saved in db
        em.detach(updatedSFTP);
        updatedSFTP
            .formatTransfer(UPDATED_FORMAT_TRANSFER)
            .user(UPDATED_USER)
            .key(UPDATED_KEY);

        restSFTPMockMvc.perform(put("/api/sftps")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSFTP)))
            .andExpect(status().isOk());

        // Validate the SFTP in the database
        List<SFTP> sFTPList = sFTPRepository.findAll();
        assertThat(sFTPList).hasSize(databaseSizeBeforeUpdate);
        SFTP testSFTP = sFTPList.get(sFTPList.size() - 1);
        assertThat(testSFTP.getFormatTransfer()).isEqualTo(UPDATED_FORMAT_TRANSFER);
        assertThat(testSFTP.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testSFTP.getKey()).isEqualTo(UPDATED_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingSFTP() throws Exception {
        int databaseSizeBeforeUpdate = sFTPRepository.findAll().size();

        // Create the SFTP

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSFTPMockMvc.perform(put("/api/sftps")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sFTP)))
            .andExpect(status().isBadRequest());

        // Validate the SFTP in the database
        List<SFTP> sFTPList = sFTPRepository.findAll();
        assertThat(sFTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSFTP() throws Exception {
        // Initialize the database
        sFTPService.save(sFTP);

        int databaseSizeBeforeDelete = sFTPRepository.findAll().size();

        // Delete the sFTP
        restSFTPMockMvc.perform(delete("/api/sftps/{id}", sFTP.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SFTP> sFTPList = sFTPRepository.findAll();
        assertThat(sFTPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
