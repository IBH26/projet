package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.Flux;
import com.mycompany.myapp.domain.DemiFlux;
import com.mycompany.myapp.repository.FluxRepository;
import com.mycompany.myapp.service.FluxService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;
import com.mycompany.myapp.service.dto.FluxCriteria;
import com.mycompany.myapp.service.FluxQueryService;

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

import com.mycompany.myapp.domain.enumeration.Env;
/**
 * Integration tests for the {@link FluxResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class FluxResourceIT {

    private static final String DEFAULT_CODE_MEGA = "AAAAAAAAAA";
    private static final String UPDATED_CODE_MEGA = "BBBBBBBBBB";

    private static final Env DEFAULT_ENVIR = Env.RECETTE;
    private static final Env UPDATED_ENVIR = Env.PPRD;

    @Autowired
    private FluxRepository fluxRepository;

    @Autowired
    private FluxService fluxService;

    @Autowired
    private FluxQueryService fluxQueryService;

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

    private MockMvc restFluxMockMvc;

    private Flux flux;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FluxResource fluxResource = new FluxResource(fluxService, fluxQueryService);
        this.restFluxMockMvc = MockMvcBuilders.standaloneSetup(fluxResource)
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
    public static Flux createEntity(EntityManager em) {
        Flux flux = new Flux()
            .codeMega(DEFAULT_CODE_MEGA)
            .envir(DEFAULT_ENVIR);
        return flux;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Flux createUpdatedEntity(EntityManager em) {
        Flux flux = new Flux()
            .codeMega(UPDATED_CODE_MEGA)
            .envir(UPDATED_ENVIR);
        return flux;
    }

    @BeforeEach
    public void initTest() {
        flux = createEntity(em);
    }

    @Test
    @Transactional
    public void createFlux() throws Exception {
        int databaseSizeBeforeCreate = fluxRepository.findAll().size();

        // Create the Flux
        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isCreated());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeCreate + 1);
        Flux testFlux = fluxList.get(fluxList.size() - 1);
        assertThat(testFlux.getCodeMega()).isEqualTo(DEFAULT_CODE_MEGA);
        assertThat(testFlux.getEnvir()).isEqualTo(DEFAULT_ENVIR);
    }

    @Test
    @Transactional
    public void createFluxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fluxRepository.findAll().size();

        // Create the Flux with an existing ID
        flux.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeMegaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fluxRepository.findAll().size();
        // set the field null
        flux.setCodeMega(null);

        // Create the Flux, which fails.

        restFluxMockMvc.perform(post("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFluxes() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList
        restFluxMockMvc.perform(get("/api/fluxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flux.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeMega").value(hasItem(DEFAULT_CODE_MEGA)))
            .andExpect(jsonPath("$.[*].envir").value(hasItem(DEFAULT_ENVIR.toString())));
    }
    
    @Test
    @Transactional
    public void getFlux() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get the flux
        restFluxMockMvc.perform(get("/api/fluxes/{id}", flux.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(flux.getId().intValue()))
            .andExpect(jsonPath("$.codeMega").value(DEFAULT_CODE_MEGA))
            .andExpect(jsonPath("$.envir").value(DEFAULT_ENVIR.toString()));
    }


    @Test
    @Transactional
    public void getFluxesByIdFiltering() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        Long id = flux.getId();

        defaultFluxShouldBeFound("id.equals=" + id);
        defaultFluxShouldNotBeFound("id.notEquals=" + id);

        defaultFluxShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFluxShouldNotBeFound("id.greaterThan=" + id);

        defaultFluxShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFluxShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFluxesByCodeMegaIsEqualToSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where codeMega equals to DEFAULT_CODE_MEGA
        defaultFluxShouldBeFound("codeMega.equals=" + DEFAULT_CODE_MEGA);

        // Get all the fluxList where codeMega equals to UPDATED_CODE_MEGA
        defaultFluxShouldNotBeFound("codeMega.equals=" + UPDATED_CODE_MEGA);
    }

    @Test
    @Transactional
    public void getAllFluxesByCodeMegaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where codeMega not equals to DEFAULT_CODE_MEGA
        defaultFluxShouldNotBeFound("codeMega.notEquals=" + DEFAULT_CODE_MEGA);

        // Get all the fluxList where codeMega not equals to UPDATED_CODE_MEGA
        defaultFluxShouldBeFound("codeMega.notEquals=" + UPDATED_CODE_MEGA);
    }

    @Test
    @Transactional
    public void getAllFluxesByCodeMegaIsInShouldWork() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where codeMega in DEFAULT_CODE_MEGA or UPDATED_CODE_MEGA
        defaultFluxShouldBeFound("codeMega.in=" + DEFAULT_CODE_MEGA + "," + UPDATED_CODE_MEGA);

        // Get all the fluxList where codeMega equals to UPDATED_CODE_MEGA
        defaultFluxShouldNotBeFound("codeMega.in=" + UPDATED_CODE_MEGA);
    }

    @Test
    @Transactional
    public void getAllFluxesByCodeMegaIsNullOrNotNull() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where codeMega is not null
        defaultFluxShouldBeFound("codeMega.specified=true");

        // Get all the fluxList where codeMega is null
        defaultFluxShouldNotBeFound("codeMega.specified=false");
    }
                @Test
    @Transactional
    public void getAllFluxesByCodeMegaContainsSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where codeMega contains DEFAULT_CODE_MEGA
        defaultFluxShouldBeFound("codeMega.contains=" + DEFAULT_CODE_MEGA);

        // Get all the fluxList where codeMega contains UPDATED_CODE_MEGA
        defaultFluxShouldNotBeFound("codeMega.contains=" + UPDATED_CODE_MEGA);
    }

    @Test
    @Transactional
    public void getAllFluxesByCodeMegaNotContainsSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where codeMega does not contain DEFAULT_CODE_MEGA
        defaultFluxShouldNotBeFound("codeMega.doesNotContain=" + DEFAULT_CODE_MEGA);

        // Get all the fluxList where codeMega does not contain UPDATED_CODE_MEGA
        defaultFluxShouldBeFound("codeMega.doesNotContain=" + UPDATED_CODE_MEGA);
    }


    @Test
    @Transactional
    public void getAllFluxesByEnvirIsEqualToSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where envir equals to DEFAULT_ENVIR
        defaultFluxShouldBeFound("envir.equals=" + DEFAULT_ENVIR);

        // Get all the fluxList where envir equals to UPDATED_ENVIR
        defaultFluxShouldNotBeFound("envir.equals=" + UPDATED_ENVIR);
    }

    @Test
    @Transactional
    public void getAllFluxesByEnvirIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where envir not equals to DEFAULT_ENVIR
        defaultFluxShouldNotBeFound("envir.notEquals=" + DEFAULT_ENVIR);

        // Get all the fluxList where envir not equals to UPDATED_ENVIR
        defaultFluxShouldBeFound("envir.notEquals=" + UPDATED_ENVIR);
    }

    @Test
    @Transactional
    public void getAllFluxesByEnvirIsInShouldWork() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where envir in DEFAULT_ENVIR or UPDATED_ENVIR
        defaultFluxShouldBeFound("envir.in=" + DEFAULT_ENVIR + "," + UPDATED_ENVIR);

        // Get all the fluxList where envir equals to UPDATED_ENVIR
        defaultFluxShouldNotBeFound("envir.in=" + UPDATED_ENVIR);
    }

    @Test
    @Transactional
    public void getAllFluxesByEnvirIsNullOrNotNull() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);

        // Get all the fluxList where envir is not null
        defaultFluxShouldBeFound("envir.specified=true");

        // Get all the fluxList where envir is null
        defaultFluxShouldNotBeFound("envir.specified=false");
    }

    @Test
    @Transactional
    public void getAllFluxesByDemiFluxIsEqualToSomething() throws Exception {
        // Initialize the database
        fluxRepository.saveAndFlush(flux);
        DemiFlux demiFlux = DemiFluxResourceIT.createEntity(em);
        em.persist(demiFlux);
        em.flush();
        flux.addDemiFlux(demiFlux);
        fluxRepository.saveAndFlush(flux);
        Long demiFluxId = demiFlux.getId();

        // Get all the fluxList where demiFlux equals to demiFluxId
        defaultFluxShouldBeFound("demiFluxId.equals=" + demiFluxId);

        // Get all the fluxList where demiFlux equals to demiFluxId + 1
        defaultFluxShouldNotBeFound("demiFluxId.equals=" + (demiFluxId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFluxShouldBeFound(String filter) throws Exception {
        restFluxMockMvc.perform(get("/api/fluxes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(flux.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeMega").value(hasItem(DEFAULT_CODE_MEGA)))
            .andExpect(jsonPath("$.[*].envir").value(hasItem(DEFAULT_ENVIR.toString())));

        // Check, that the count call also returns 1
        restFluxMockMvc.perform(get("/api/fluxes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFluxShouldNotBeFound(String filter) throws Exception {
        restFluxMockMvc.perform(get("/api/fluxes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFluxMockMvc.perform(get("/api/fluxes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFlux() throws Exception {
        // Get the flux
        restFluxMockMvc.perform(get("/api/fluxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFlux() throws Exception {
        // Initialize the database
        fluxService.save(flux);

        int databaseSizeBeforeUpdate = fluxRepository.findAll().size();

        // Update the flux
        Flux updatedFlux = fluxRepository.findById(flux.getId()).get();
        // Disconnect from session so that the updates on updatedFlux are not directly saved in db
        em.detach(updatedFlux);
        updatedFlux
            .codeMega(UPDATED_CODE_MEGA)
            .envir(UPDATED_ENVIR);

        restFluxMockMvc.perform(put("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFlux)))
            .andExpect(status().isOk());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeUpdate);
        Flux testFlux = fluxList.get(fluxList.size() - 1);
        assertThat(testFlux.getCodeMega()).isEqualTo(UPDATED_CODE_MEGA);
        assertThat(testFlux.getEnvir()).isEqualTo(UPDATED_ENVIR);
    }

    @Test
    @Transactional
    public void updateNonExistingFlux() throws Exception {
        int databaseSizeBeforeUpdate = fluxRepository.findAll().size();

        // Create the Flux

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFluxMockMvc.perform(put("/api/fluxes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(flux)))
            .andExpect(status().isBadRequest());

        // Validate the Flux in the database
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFlux() throws Exception {
        // Initialize the database
        fluxService.save(flux);

        int databaseSizeBeforeDelete = fluxRepository.findAll().size();

        // Delete the flux
        restFluxMockMvc.perform(delete("/api/fluxes/{id}", flux.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Flux> fluxList = fluxRepository.findAll();
        assertThat(fluxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
