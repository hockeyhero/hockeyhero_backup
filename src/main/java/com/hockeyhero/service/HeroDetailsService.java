package com.hockeyhero.service;

import com.hockeyhero.service.dto.HeroDetailsDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.hockeyhero.domain.HeroDetails}.
 */
public interface HeroDetailsService {
  /**
   * Save a heroDetails.
   *
   * @param heroDetailsDTO the entity to save.
   * @return the persisted entity.
   */
  HeroDetailsDTO save(HeroDetailsDTO heroDetailsDTO);

  /**
   * Updates a heroDetails.
   *
   * @param heroDetailsDTO the entity to update.
   * @return the persisted entity.
   */
  HeroDetailsDTO update(HeroDetailsDTO heroDetailsDTO);

  /**
   * Partially updates a heroDetails.
   *
   * @param heroDetailsDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<HeroDetailsDTO> partialUpdate(HeroDetailsDTO heroDetailsDTO);

  /**
   * Get all the heroDetails.
   *
   * @return the list of entities.
   */
  List<HeroDetailsDTO> findAll();
  /**
   * Get all the HeroDetailsDTO where HeroKeys is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<HeroDetailsDTO> findAllWhereHeroKeysIsNull();

  /**
   * Get the "id" heroDetails.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<HeroDetailsDTO> findOne(Long id);

  /**
   * Delete the "id" heroDetails.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
