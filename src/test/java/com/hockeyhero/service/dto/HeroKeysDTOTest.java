package com.hockeyhero.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.hockeyhero.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HeroKeysDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(HeroKeysDTO.class);
    HeroKeysDTO heroKeysDTO1 = new HeroKeysDTO();
    heroKeysDTO1.setId(1L);
    HeroKeysDTO heroKeysDTO2 = new HeroKeysDTO();
    assertThat(heroKeysDTO1).isNotEqualTo(heroKeysDTO2);
    heroKeysDTO2.setId(heroKeysDTO1.getId());
    assertThat(heroKeysDTO1).isEqualTo(heroKeysDTO2);
    heroKeysDTO2.setId(2L);
    assertThat(heroKeysDTO1).isNotEqualTo(heroKeysDTO2);
    heroKeysDTO1.setId(null);
    assertThat(heroKeysDTO1).isNotEqualTo(heroKeysDTO2);
  }
}
