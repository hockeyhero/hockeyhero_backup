package com.hockeyhero.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.hockeyhero.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HeroDetailsDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(HeroDetailsDTO.class);
    HeroDetailsDTO heroDetailsDTO1 = new HeroDetailsDTO();
    heroDetailsDTO1.setId(1L);
    HeroDetailsDTO heroDetailsDTO2 = new HeroDetailsDTO();
    assertThat(heroDetailsDTO1).isNotEqualTo(heroDetailsDTO2);
    heroDetailsDTO2.setId(heroDetailsDTO1.getId());
    assertThat(heroDetailsDTO1).isEqualTo(heroDetailsDTO2);
    heroDetailsDTO2.setId(2L);
    assertThat(heroDetailsDTO1).isNotEqualTo(heroDetailsDTO2);
    heroDetailsDTO1.setId(null);
    assertThat(heroDetailsDTO1).isNotEqualTo(heroDetailsDTO2);
  }
}
