package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.ContactProjet;
import com.mycompany.myapp.repository.ContactProjetRepository;
import com.mycompany.myapp.service.ContactProjetService;
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
 * Integration tests for the {@link ContactProjetResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class ContactProjetResourceIT {

    private static final String DEFAULT_NAME_CP = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CP = "BBBBBBBBBB";

    private static final String DEFAULT_FUNCTION_CP = "AAAAAAAAAA";
    private static final String UPDATED_FUNCTION_CP = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER_CP = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER_CP = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_CP = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_CP = "BBBBBBBBBB";

    @Autowired
    private ContactProjetRepository contactProjetRepository;

    @Autowired
    private ContactProjetService contactProjetService;

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

    private MockMvc restContactProjetMockMvc;

    private ContactProjet contactProjet;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContactProjetResource contactProjetResource = new ContactProjetResource(contactProjetService);
        this.restContactProjetMockMvc = MockMvcBuilders.standaloneSetup(contactProjetResource)
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
    public static ContactProjet createEntity(EntityManager em) {
        ContactProjet contactProjet = new ContactProjet()
            .nameCP(DEFAULT_NAME_CP)
            .functionCP(DEFAULT_FUNCTION_CP)
            .phoneNumberCP(DEFAULT_PHONE_NUMBER_CP)
            .emailCP(DEFAULT_EMAIL_CP);
        return contactProjet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactProjet createUpdatedEntity(EntityManager em) {
        ContactProjet contactProjet = new ContactProjet()
            .nameCP(UPDATED_NAME_CP)
            .functionCP(UPDATED_FUNCTION_CP)
            .phoneNumberCP(UPDATED_PHONE_NUMBER_CP)
            .emailCP(UPDATED_EMAIL_CP);
        return contactProjet;
    }

    @BeforeEach
    public void initTest() {
        contactProjet = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactProjet() throws Exception {
        int databaseSizeBeforeCreate = contactProjetRepository.findAll().size();

        // Create the ContactProjet
        restContactProjetMockMvc.perform(post("/api/contact-projets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactProjet)))
            .andExpect(status().isCreated());

        // Validate the ContactProjet in the database
        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeCreate + 1);
        ContactProjet testContactProjet = contactProjetList.get(contactProjetList.size() - 1);
        assertThat(testContactProjet.getNameCP()).isEqualTo(DEFAULT_NAME_CP);
        assertThat(testContactProjet.getFunctionCP()).isEqualTo(DEFAULT_FUNCTION_CP);
        assertThat(testContactProjet.getPhoneNumberCP()).isEqualTo(DEFAULT_PHONE_NUMBER_CP);
        assertThat(testContactProjet.getEmailCP()).isEqualTo(DEFAULT_EMAIL_CP);
    }

    @Test
    @Transactional
    public void createContactProjetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactProjetRepository.findAll().size();

        // Create the ContactProjet with an existing ID
        contactProjet.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactProjetMockMvc.perform(post("/api/contact-projets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactProjet)))
            .andExpect(status().isBadRequest());

        // Validate the ContactProjet in the database
        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameCPIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactProjetRepository.findAll().size();
        // set the field null
        contactProjet.setNameCP(null);

        // Create the ContactProjet, which fails.

        restContactProjetMockMvc.perform(post("/api/contact-projets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactProjet)))
            .andExpect(status().isBadRequest());

        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFunctionCPIsRequired() throws Exception {
        int databaseSizeBeforeTest = contactProjetRepository.findAll().size();
        // set the field null
        contactProjet.setFunctionCP(null);

        // Create the ContactProjet, which fails.

        restContactProjetMockMvc.perform(post("/api/contact-projets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactProjet)))
            .andExpect(status().isBadRequest());

        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContactProjets() throws Exception {
        // Initialize the database
        contactProjetRepository.saveAndFlush(contactProjet);

        // Get all the contactProjetList
        restContactProjetMockMvc.perform(get("/api/contact-projets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactProjet.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameCP").value(hasItem(DEFAULT_NAME_CP)))
            .andExpect(jsonPath("$.[*].functionCP").value(hasItem(DEFAULT_FUNCTION_CP)))
            .andExpect(jsonPath("$.[*].phoneNumberCP").value(hasItem(DEFAULT_PHONE_NUMBER_CP)))
            .andExpect(jsonPath("$.[*].emailCP").value(hasItem(DEFAULT_EMAIL_CP)));
    }
    
    @Test
    @Transactional
    public void getContactProjet() throws Exception {
        // Initialize the database
        contactProjetRepository.saveAndFlush(contactProjet);

        // Get the contactProjet
        restContactProjetMockMvc.perform(get("/api/contact-projets/{id}", contactProjet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contactProjet.getId().intValue()))
            .andExpect(jsonPath("$.nameCP").value(DEFAULT_NAME_CP))
            .andExpect(jsonPath("$.functionCP").value(DEFAULT_FUNCTION_CP))
            .andExpect(jsonPath("$.phoneNumberCP").value(DEFAULT_PHONE_NUMBER_CP))
            .andExpect(jsonPath("$.emailCP").value(DEFAULT_EMAIL_CP));
    }

    @Test
    @Transactional
    public void getNonExistingContactProjet() throws Exception {
        // Get the contactProjet
        restContactProjetMockMvc.perform(get("/api/contact-projets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactProjet() throws Exception {
        // Initialize the database
        contactProjetService.save(contactProjet);

        int databaseSizeBeforeUpdate = contactProjetRepository.findAll().size();

        // Update the contactProjet
        ContactProjet updatedContactProjet = contactProjetRepository.findById(contactProjet.getId()).get();
        // Disconnect from session so that the updates on updatedContactProjet are not directly saved in db
        em.detach(updatedContactProjet);
        updatedContactProjet
            .nameCP(UPDATED_NAME_CP)
            .functionCP(UPDATED_FUNCTION_CP)
            .phoneNumberCP(UPDATED_PHONE_NUMBER_CP)
            .emailCP(UPDATED_EMAIL_CP);

        restContactProjetMockMvc.perform(put("/api/contact-projets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedContactProjet)))
            .andExpect(status().isOk());

        // Validate the ContactProjet in the database
        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeUpdate);
        ContactProjet testContactProjet = contactProjetList.get(contactProjetList.size() - 1);
        assertThat(testContactProjet.getNameCP()).isEqualTo(UPDATED_NAME_CP);
        assertThat(testContactProjet.getFunctionCP()).isEqualTo(UPDATED_FUNCTION_CP);
        assertThat(testContactProjet.getPhoneNumberCP()).isEqualTo(UPDATED_PHONE_NUMBER_CP);
        assertThat(testContactProjet.getEmailCP()).isEqualTo(UPDATED_EMAIL_CP);
    }

    @Test
    @Transactional
    public void updateNonExistingContactProjet() throws Exception {
        int databaseSizeBeforeUpdate = contactProjetRepository.findAll().size();

        // Create the ContactProjet

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactProjetMockMvc.perform(put("/api/contact-projets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactProjet)))
            .andExpect(status().isBadRequest());

        // Validate the ContactProjet in the database
        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContactProjet() throws Exception {
        // Initialize the database
        contactProjetService.save(contactProjet);

        int databaseSizeBeforeDelete = contactProjetRepository.findAll().size();

        // Delete the contactProjet
        restContactProjetMockMvc.perform(delete("/api/contact-projets/{id}", contactProjet.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactProjet> contactProjetList = contactProjetRepository.findAll();
        assertThat(contactProjetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
