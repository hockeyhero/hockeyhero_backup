package com.hockeyhero.service.impl;

import com.hockeyhero.domain.HeroKeys;
import com.hockeyhero.repository.HeroKeysRepository;
import com.hockeyhero.service.HeroKeysService;
import com.hockeyhero.service.dto.HeroKeysDTO;
import com.hockeyhero.service.mapper.HeroKeysMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HeroKeys}.
 */
@Service
@Transactional
public class HeroKeysServiceImpl implements HeroKeysService {

  private final Logger log = LoggerFactory.getLogger(HeroKeysServiceImpl.class);

  private final HeroKeysRepository heroKeysRepository;

  private final HeroKeysMapper heroKeysMapper;

  public HeroKeysServiceImpl(HeroKeysRepository heroKeysRepository, HeroKeysMapper heroKeysMapper) {
    this.heroKeysRepository = heroKeysRepository;
    this.heroKeysMapper = heroKeysMapper;
  }

  @Override
  public HeroKeysDTO save(HeroKeysDTO heroKeysDTO) {
    log.debug("Request to save HeroKeys : {}", heroKeysDTO);
    HeroKeys heroKeys = heroKeysMapper.toEntity(heroKeysDTO);
    heroKeys = heroKeysRepository.save(heroKeys);
    return heroKeysMapper.toDto(heroKeys);
  }

  @Override
  public HeroKeysDTO update(HeroKeysDTO heroKeysDTO) {
    log.debug("Request to update HeroKeys : {}", heroKeysDTO);
    HeroKeys heroKeys = heroKeysMapper.toEntity(heroKeysDTO);
    heroKeys = heroKeysRepository.save(heroKeys);
    return heroKeysMapper.toDto(heroKeys);
  }

  @Override
  public Optional<HeroKeysDTO> partialUpdate(HeroKeysDTO heroKeysDTO) {
    log.debug("Request to partially update HeroKeys : {}", heroKeysDTO);

    return heroKeysRepository
      .findById(heroKeysDTO.getId())
      .map(existingHeroKeys -> {
        heroKeysMapper.partialUpdate(existingHeroKeys, heroKeysDTO);

        return existingHeroKeys;
      })
      .map(heroKeysRepository::save)
      .map(heroKeysMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<HeroKeysDTO> findAll() {
    log.debug("Request to get all HeroKeys");
    return heroKeysRepository.findAll().stream().map(heroKeysMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<HeroKeysDTO> findOne(Long id) {
    log.debug("Request to get HeroKeys : {}", id);
    return heroKeysRepository.findById(id).map(heroKeysMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete HeroKeys : {}", id);
    heroKeysRepository.deleteById(id);
  }
}
