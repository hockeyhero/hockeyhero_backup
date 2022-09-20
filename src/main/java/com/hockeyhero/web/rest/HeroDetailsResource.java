package com.hockeyhero.web.rest;

import com.hockeyhero.repository.HeroDetailsRepository;
import com.hockeyhero.service.HeroDetailsService;
import com.hockeyhero.service.dto.HeroDetailsDTO;
import com.hockeyhero.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.hockeyhero.domain.HeroDetails}.
 */
@RestController
@RequestMapping("/api")
public class HeroDetailsResource {

  private final Logger log = LoggerFactory.getLogger(HeroDetailsResource.class);

  private static final String ENTITY_NAME = "heroDetails";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final HeroDetailsService heroDetailsService;

  private final HeroDetailsRepository heroDetailsRepository;

  public HeroDetailsResource(HeroDetailsService heroDetailsService, HeroDetailsRepository heroDetailsRepository) {
    this.heroDetailsService = heroDetailsService;
    this.heroDetailsRepository = heroDetailsRepository;
  }

  /**
   * {@code POST  /hero-details} : Create a new heroDetails.
   *
   * @param heroDetailsDTO the heroDetailsDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new heroDetailsDTO, or with status {@code 400 (Bad Request)} if the heroDetails has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/hero-details")
  public ResponseEntity<HeroDetailsDTO> createHeroDetails(@RequestBody HeroDetailsDTO heroDetailsDTO) throws URISyntaxException {
    log.debug("REST request to save HeroDetails : {}", heroDetailsDTO);
    if (heroDetailsDTO.getId() != null) {
      throw new BadRequestAlertException("A new heroDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    HeroDetailsDTO result = heroDetailsService.save(heroDetailsDTO);
    return ResponseEntity
      .created(new URI("/api/hero-details/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /hero-details/:id} : Updates an existing heroDetails.
   *
   * @param id the id of the heroDetailsDTO to save.
   * @param heroDetailsDTO the heroDetailsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated heroDetailsDTO,
   * or with status {@code 400 (Bad Request)} if the heroDetailsDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the heroDetailsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/hero-details/{id}")
  public ResponseEntity<HeroDetailsDTO> updateHeroDetails(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody HeroDetailsDTO heroDetailsDTO
  ) throws URISyntaxException {
    log.debug("REST request to update HeroDetails : {}, {}", id, heroDetailsDTO);
    if (heroDetailsDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, heroDetailsDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!heroDetailsRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    HeroDetailsDTO result = heroDetailsService.update(heroDetailsDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, heroDetailsDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /hero-details/:id} : Partial updates given fields of an existing heroDetails, field will ignore if it is null
   *
   * @param id the id of the heroDetailsDTO to save.
   * @param heroDetailsDTO the heroDetailsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated heroDetailsDTO,
   * or with status {@code 400 (Bad Request)} if the heroDetailsDTO is not valid,
   * or with status {@code 404 (Not Found)} if the heroDetailsDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the heroDetailsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/hero-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
  public ResponseEntity<HeroDetailsDTO> partialUpdateHeroDetails(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody HeroDetailsDTO heroDetailsDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update HeroDetails partially : {}, {}", id, heroDetailsDTO);
    if (heroDetailsDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, heroDetailsDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!heroDetailsRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<HeroDetailsDTO> result = heroDetailsService.partialUpdate(heroDetailsDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, heroDetailsDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /hero-details} : get all the heroDetails.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of heroDetails in body.
   */
  @GetMapping("/hero-details")
  public List<HeroDetailsDTO> getAllHeroDetails(@RequestParam(required = false) String filter) {
    if ("herokeys-is-null".equals(filter)) {
      log.debug("REST request to get all HeroDetailss where heroKeys is null");
      return heroDetailsService.findAllWhereHeroKeysIsNull();
    }
    log.debug("REST request to get all HeroDetails");
    return heroDetailsService.findAll();
  }

  /**
   * {@code GET  /hero-details/:id} : get the "id" heroDetails.
   *
   * @param id the id of the heroDetailsDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the heroDetailsDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/hero-details/{id}")
  public ResponseEntity<HeroDetailsDTO> getHeroDetails(@PathVariable Long id) {
    log.debug("REST request to get HeroDetails : {}", id);
    Optional<HeroDetailsDTO> heroDetailsDTO = heroDetailsService.findOne(id);
    return ResponseUtil.wrapOrNotFound(heroDetailsDTO);
  }

  /**
   * {@code DELETE  /hero-details/:id} : delete the "id" heroDetails.
   *
   * @param id the id of the heroDetailsDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/hero-details/{id}")
  public ResponseEntity<Void> deleteHeroDetails(@PathVariable Long id) {
    log.debug("REST request to delete HeroDetails : {}", id);
    heroDetailsService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
