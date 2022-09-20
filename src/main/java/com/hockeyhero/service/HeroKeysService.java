package com.hockeyhero.service;

import com.hockeyhero.service.dto.HeroKeysDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.hockeyhero.domain.HeroKeys}.
 */
public interface HeroKeysService {
  /**
   * Save a heroKeys.
   *
   * @param heroKeysDTO the entity to save.
   * @return the persisted entity.
   */
  HeroKeysDTO save(HeroKeysDTO heroKeysDTO);

  /**
   * Updates a heroKeys.
   *
   * @param heroKeysDTO the entity to update.
   * @return the persisted entity.
   */
  HeroKeysDTO update(HeroKeysDTO heroKeysDTO);

  /**
   * Partially updates a heroKeys.
   *
   * @param heroKeysDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<HeroKeysDTO> partialUpdate(HeroKeysDTO heroKeysDTO);

  /**
   * Get all the heroKeys.
   *
   * @return the list of entities.
   */
  List<HeroKeysDTO> findAll();

  /**
   * Get the "id" heroKeys.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<HeroKeysDTO> findOne(Long id);

  /**
   * Delete the "id" heroKeys.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);
}
