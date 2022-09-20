package com.hockeyhero.service.mapper;

import com.hockeyhero.domain.HeroDetails;
import com.hockeyhero.domain.User;
import com.hockeyhero.service.dto.HeroDetailsDTO;
import com.hockeyhero.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HeroDetails} and its DTO {@link HeroDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface HeroDetailsMapper extends EntityMapper<HeroDetailsDTO, HeroDetails> {
  @Mapping(target = "user", source = "user", qualifiedByName = "userId")
  HeroDetailsDTO toDto(HeroDetails s);

  @Named("userId")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  UserDTO toDtoUserId(User user);
}
