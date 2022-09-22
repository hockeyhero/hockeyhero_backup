package com.hockeyhero.web.rest;

import com.hockeyhero.repository.HeroKeysRepository;
import com.hockeyhero.service.HeroKeysService;
import com.hockeyhero.service.dto.HeroKeysDTO;
import com.hockeyhero.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.hockeyhero.domain.HeroKeys}.
 */
@RestController
@RequestMapping("/api")
public class HeroKeysResource {

  private final Logger log = LoggerFactory.getLogger(HeroKeysResource.class);

  private static final String ENTITY_NAME = "heroKeys";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final HeroKeysService heroKeysService;

  private final HeroKeysRepository heroKeysRepository;

  public HeroKeysResource(HeroKeysService heroKeysService, HeroKeysRepository heroKeysRepository) {
    this.heroKeysService = heroKeysService;
    this.heroKeysRepository = heroKeysRepository;
  }

  /**
   * {@code POST  /hero-keys} : Create a new heroKeys.
   *
   * @param heroKeysDTO the heroKeysDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new heroKeysDTO, or with status {@code 400 (Bad Request)} if the heroKeys has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/hero-keys")
  public ResponseEntity<HeroKeysDTO> createHeroKeys(@RequestBody HeroKeysDTO heroKeysDTO) throws URISyntaxException {
    log.debug("REST request to save HeroKeys : {}", heroKeysDTO);
    if (heroKeysDTO.getId() != null) {
      throw new BadRequestAlertException("A new heroKeys cannot already have an ID", ENTITY_NAME, "idexists");
    }
    HeroKeysDTO result = heroKeysService.save(heroKeysDTO);
    return ResponseEntity
      .created(new URI("/api/hero-keys/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /hero-keys/:id} : Updates an existing heroKeys.
   *
   * @param id the id of the heroKeysDTO to save.
   * @param heroKeysDTO the heroKeysDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated heroKeysDTO,
   * or with status {@code 400 (Bad Request)} if the heroKeysDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the heroKeysDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/hero-keys/{id}")
  public ResponseEntity<HeroKeysDTO> updateHeroKeys(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody HeroKeysDTO heroKeysDTO
  ) throws URISyntaxException {
    log.debug("REST request to update HeroKeys : {}, {}", id, heroKeysDTO);
    if (heroKeysDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, heroKeysDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!heroKeysRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    HeroKeysDTO result = heroKeysService.update(heroKeysDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, heroKeysDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /hero-keys/:id} : Partial updates given fields of an existing heroKeys, field will ignore if it is null
   *
   * @param id the id of the heroKeysDTO to save.
   * @param heroKeysDTO the heroKeysDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated heroKeysDTO,
   * or with status {@code 400 (Bad Request)} if the heroKeysDTO is not valid,
   * or with status {@code 404 (Not Found)} if the heroKeysDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the heroKeysDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/hero-keys/{id}", consumes = { "application/json", "application/merge-patch+json" })
  public ResponseEntity<HeroKeysDTO> partialUpdateHeroKeys(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody HeroKeysDTO heroKeysDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update HeroKeys partially : {}, {}", id, heroKeysDTO);
    if (heroKeysDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, heroKeysDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!heroKeysRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<HeroKeysDTO> result = heroKeysService.partialUpdate(heroKeysDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, heroKeysDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /hero-keys} : get all the heroKeys.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of heroKeys in body.
   */
  @GetMapping("/hero-keys")
  public List<HeroKeysDTO> getAllHeroKeys() {
    log.debug("REST request to get all HeroKeys");
    return heroKeysService.findAll();
  }

  /**
   * {@code GET  /hero-keys/:id} : get the "id" heroKeys.
   *
   * @param id the id of the heroKeysDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the heroKeysDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/hero-keys/{id}")
  public ResponseEntity<HeroKeysDTO> getHeroKeys(@PathVariable Long id) {
    log.debug("REST request to get HeroKeys : {}", id);
    Optional<HeroKeysDTO> heroKeysDTO = heroKeysService.findOne(id);
    return ResponseUtil.wrapOrNotFound(heroKeysDTO);
  }

  /**
   * {@code DELETE  /hero-keys/:id} : delete the "id" heroKeys.
   *
   * @param id the id of the heroKeysDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/hero-keys/{id}")
  public ResponseEntity<Void> deleteHeroKeys(@PathVariable Long id) {
    log.debug("REST request to delete HeroKeys : {}", id);
    heroKeysService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }
}
