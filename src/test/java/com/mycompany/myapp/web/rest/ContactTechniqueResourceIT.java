package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.ContactTechnique;
import com.mycompany.myapp.repository.ContactTechniqueRepository;
import com.mycompany.myapp.service.ContactTechniqueService;
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
 * Integration tests for the {@link ContactTechniqueResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class ContactTechniqueResourceIT {

    private static final String DEFAULT_NAME_CT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CT = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_CT = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_CT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER_CT = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER_CT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_CT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_CT = "BBBBBBBBBB";

    @Autowired
    private ContactTechniqueRepository contactTechniqueRepository;

    @Autowired
    private ContactTechniqueService contactTechniqueService;

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

    private MockMvc restContactTechniqueMockMvc;

    private ContactTechnique contactTechnique;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactTechniqueResource contactTechniqueResource = new ContactTechniqueResource(contactTechniqueService);
        this.restContactTechniqueMockMvc = MockMvcBuilders.standaloneSetup(contactTechniqueResource)
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
    public static ContactTechnique createEntity(EntityManager em) {
        ContactTechnique contactTechnique = new ContactTechnique()
            .nameCT(DEFAULT_NAME_CT)
            .functionCT(DEFAULT_FUNCTION_CT)
            .phoneNumberCT(DEFAULT_PHONE_NUMBER_CT)
            .emailCT(DEFAULT_EMAIL_CT);
        return contactTechnique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactTechnique createUpdatedEntity(EntityManager em) {
        ContactTechnique contactTechnique = new ContactTechnique()
            .nameCT(UPDATED_NAME_CT)
            .functionCT(UPDATED_FUNCTION_CT)
            .phoneNumberCT(UPDATED_PHONE_NUMBER_CT)
            .emailCT(UPDATED_EMAIL_CT);
        return contactTechnique;
    }

    @BeforeEach
    public void initTest() {
        contactTechnique = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactTechnique() throws Exception {
        int databaseSizeBeforeCreate = contactTechniqueRepository.findAll().size();

        // Create the ContactTechnique
        restContactTechniqueMockMvc.perform(post("/api/contact-techniques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactTechnique)))
            .andExpect(status().isCreated());

        // Validate the ContactTechnique in the database
        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeCreate + 1);
        ContactTechnique testContactTechnique = contactTechniqueList.get(contactTechniqueList.size() - 1);
        assertThat(testContactTechnique.getNameCT()).isEqualTo(DEFAULT_NAME_CT);
        assertThat(testContactTechnique.getFunctionCT()).isEqualTo(DEFAULT_FUNCTION_CT);
        assertThat(testContactTechnique.getPhoneNumberCT()).isEqualTo(DEFAULT_PHONE_NUMBER_CT);
        assertThat(testContactTechnique.getEmailCT()).isEqualTo(DEFAULT_EMAIL_CT);
    }

    @Test
    @Transactional
    public void createContactTechniqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactTechniqueRepository.findAll().size();

        // Create the ContactTechnique with an existing ID
        contactTechnique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactTechniqueMockMvc.perform(post("/api/contact-techniques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactTechnique)))
            .andExpect(status().isBadRequest());

        // Validate the ContactTechnique in the database
        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameCTIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactTechniqueRepository.findAll().size();
        // set the field null
        contactTechnique.setNameCT(null);

        // Create the ContactTechnique, which fails.

        restContactTechniqueMockMvc.perform(post("/api/contact-techniques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactTechnique)))
            .andExpect(status().isBadRequest());

        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFunctionCTIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactTechniqueRepository.findAll().size();
        // set the field null
        contactTechnique.setFunctionCT(null);

        // Create the ContactTechnique, which fails.

        restContactTechniqueMockMvc.perform(post("/api/contact-techniques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactTechnique)))
            .andExpect(status().isBadRequest());

        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContactTechniques() throws Exception {
        // Initialize the database
        contactTechniqueRepository.saveAndFlush(contactTechnique);

        // Get all the contactTechniqueList
        restContactTechniqueMockMvc.perform(get("/api/contact-techniques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactTechnique.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameCT").value(hasItem(DEFAULT_NAME_CT)))
            .andExpect(jsonPath("$.[*].functionCT").value(hasItem(DEFAULT_FUNCTION_CT)))
            .andExpect(jsonPath("$.[*].phoneNumberCT").value(hasItem(DEFAULT_PHONE_NUMBER_CT)))
            .andExpect(jsonPath("$.[*].emailCT").value(hasItem(DEFAULT_EMAIL_CT)));
    }
    
    @Test
    @Transactional
    public void getContactTechnique() throws Exception {
        // Initialize the database
        contactTechniqueRepository.saveAndFlush(contactTechnique);

        // Get the contactTechnique
        restContactTechniqueMockMvc.perform(get("/api/contact-techniques/{id}", contactTechnique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contactTechnique.getId().intValue()))
            .andExpect(jsonPath("$.nameCT").value(DEFAULT_NAME_CT))
            .andExpect(jsonPath("$.functionCT").value(DEFAULT_FUNCTION_CT))
            .andExpect(jsonPath("$.phoneNumberCT").value(DEFAULT_PHONE_NUMBER_CT))
            .andExpect(jsonPath("$.emailCT").value(DEFAULT_EMAIL_CT));
    }

    @Test
    @Transactional
    public void getNonExistingContactTechnique() throws Exception {
        // Get the contactTechnique
        restContactTechniqueMockMvc.perform(get("/api/contact-techniques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactTechnique() throws Exception {
        // Initialize the database
        contactTechniqueService.save(contactTechnique);

        int databaseSizeBeforeUpdate = contactTechniqueRepository.findAll().size();

        // Update the contactTechnique
        ContactTechnique updatedContactTechnique = contactTechniqueRepository.findById(contactTechnique.getId()).get();
        // Disconnect from session so that the updates on updatedContactTechnique are not directly saved in db
        em.detach(updatedContactTechnique);
        updatedContactTechnique
            .nameCT(UPDATED_NAME_CT)
            .functionCT(UPDATED_FUNCTION_CT)
            .phoneNumberCT(UPDATED_PHONE_NUMBER_CT)
            .emailCT(UPDATED_EMAIL_CT);

        restContactTechniqueMockMvc.perform(put("/api/contact-techniques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedContactTechnique)))
            .andExpect(status().isOk());

        // Validate the ContactTechnique in the database
        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeUpdate);
        ContactTechnique testContactTechnique = contactTechniqueList.get(contactTechniqueList.size() - 1);
        assertThat(testContactTechnique.getNameCT()).isEqualTo(UPDATED_NAME_CT);
        assertThat(testContactTechnique.getFunctionCT()).isEqualTo(UPDATED_FUNCTION_CT);
        assertThat(testContactTechnique.getPhoneNumberCT()).isEqualTo(UPDATED_PHONE_NUMBER_CT);
        assertThat(testContactTechnique.getEmailCT()).isEqualTo(UPDATED_EMAIL_CT);
    }

    @Test
    @Transactional
    public void updateNonExistingContactTechnique() throws Exception {
        int databaseSizeBeforeUpdate = contactTechniqueRepository.findAll().size();

        // Create the ContactTechnique

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactTechniqueMockMvc.perform(put("/api/contact-techniques")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactTechnique)))
            .andExpect(status().isBadRequest());

        // Validate the ContactTechnique in the database
        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContactTechnique() throws Exception {
        // Initialize the database
        contactTechniqueService.save(contactTechnique);

        int databaseSizeBeforeDelete = contactTechniqueRepository.findAll().size();

        // Delete the contactTechnique
        restContactTechniqueMockMvc.perform(delete("/api/contact-techniques/{id}", contactTechnique.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactTechnique> contactTechniqueList = contactTechniqueRepository.findAll();
        assertThat(contactTechniqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
