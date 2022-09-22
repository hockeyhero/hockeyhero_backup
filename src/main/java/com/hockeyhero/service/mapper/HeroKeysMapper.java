package com.hockeyhero.service.mapper;

import com.hockeyhero.domain.HeroDetails;
import com.hockeyhero.domain.HeroKeys;
import com.hockeyhero.service.dto.HeroDetailsDTO;
import com.hockeyhero.service.dto.HeroKeysDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HeroKeys} and its DTO {@link HeroKeysDTO}.
 */
@Mapper(componentModel = "spring")
public interface HeroKeysMapper extends EntityMapper<HeroKeysDTO, HeroKeys> {
  @Mapping(target = "heroDetails", source = "heroDetails", qualifiedByName = "heroDetailsId")
  HeroKeysDTO toDto(HeroKeys s);

  @Named("heroDetailsId")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  HeroDetailsDTO toDtoHeroDetailsId(HeroDetails heroDetails);
}
