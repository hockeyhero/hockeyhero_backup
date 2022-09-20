package com.hockeyhero.service.impl;

import com.hockeyhero.domain.HeroDetails;
import com.hockeyhero.repository.HeroDetailsRepository;
import com.hockeyhero.service.HeroDetailsService;
import com.hockeyhero.service.dto.HeroDetailsDTO;
import com.hockeyhero.service.mapper.HeroDetailsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HeroDetails}.
 */
@Service
@Transactional
public class HeroDetailsServiceImpl implements HeroDetailsService {

  private final Logger log = LoggerFactory.getLogger(HeroDetailsServiceImpl.class);

  private final HeroDetailsRepository heroDetailsRepository;

  private final HeroDetailsMapper heroDetailsMapper;

  public HeroDetailsServiceImpl(HeroDetailsRepository heroDetailsRepository, HeroDetailsMapper heroDetailsMapper) {
    this.heroDetailsRepository = heroDetailsRepository;
    this.heroDetailsMapper = heroDetailsMapper;
  }

  @Override
  public HeroDetailsDTO save(HeroDetailsDTO heroDetailsDTO) {
    log.debug("Request to save HeroDetails : {}", heroDetailsDTO);
    HeroDetails heroDetails = heroDetailsMapper.toEntity(heroDetailsDTO);
    heroDetails = heroDetailsRepository.save(heroDetails);
    return heroDetailsMapper.toDto(heroDetails);
  }

  @Override
  public HeroDetailsDTO update(HeroDetailsDTO heroDetailsDTO) {
    log.debug("Request to update HeroDetails : {}", heroDetailsDTO);
    HeroDetails heroDetails = heroDetailsMapper.toEntity(heroDetailsDTO);
    heroDetails = heroDetailsRepository.save(heroDetails);
    return heroDetailsMapper.toDto(heroDetails);
  }

  @Override
  public Optional<HeroDetailsDTO> partialUpdate(HeroDetailsDTO heroDetailsDTO) {
    log.debug("Request to partially update HeroDetails : {}", heroDetailsDTO);

    return heroDetailsRepository
      .findById(heroDetailsDTO.getId())
      .map(existingHeroDetails -> {
        heroDetailsMapper.partialUpdate(existingHeroDetails, heroDetailsDTO);

        return existingHeroDetails;
      })
      .map(heroDetailsRepository::save)
      .map(heroDetailsMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<HeroDetailsDTO> findAll() {
    log.debug("Request to get all HeroDetails");
    return heroDetailsRepository.findAll().stream().map(heroDetailsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the heroDetails where HeroKeys is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<HeroDetailsDTO> findAllWhereHeroKeysIsNull() {
    log.debug("Request to get all heroDetails where HeroKeys is null");
    return StreamSupport
      .stream(heroDetailsRepository.findAll().spliterator(), false)
      .filter(heroDetails -> heroDetails.getHeroKeys() == null)
      .map(heroDetailsMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<HeroDetailsDTO> findOne(Long id) {
    log.debug("Request to get HeroDetails : {}", id);
    return heroDetailsRepository.findById(id).map(heroDetailsMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete HeroDetails : {}", id);
    heroDetailsRepository.deleteById(id);
  }
}
