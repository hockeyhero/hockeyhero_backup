package com.hockeyhero.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.hockeyhero.domain.HeroKeys} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HeroKeysDTO implements Serializable {

  private Long id;

  private Boolean hideMe;

  private Double latitude;

  private Double longitude;

  private Integer age;

  private Integer myPosition;

  private Integer skill;

  private HeroDetailsDTO heroDetails;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Boolean getHideMe() {
    return hideMe;
  }

  public void setHideMe(Boolean hideMe) {
    this.hideMe = hideMe;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getMyPosition() {
    return myPosition;
  }

  public void setMyPosition(Integer myPosition) {
    this.myPosition = myPosition;
  }

  public Integer getSkill() {
    return skill;
  }

  public void setSkill(Integer skill) {
    this.skill = skill;
  }

  public HeroDetailsDTO getHeroDetails() {
    return heroDetails;
  }

  public void setHeroDetails(HeroDetailsDTO heroDetails) {
    this.heroDetails = heroDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HeroKeysDTO)) {
      return false;
    }

    HeroKeysDTO heroKeysDTO = (HeroKeysDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, heroKeysDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "HeroKeysDTO{" +
            "id=" + getId() +
            ", hideMe='" + getHideMe() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", age=" + getAge() +
            ", myPosition=" + getMyPosition() +
            ", skill=" + getSkill() +
            ", heroDetails=" + getHeroDetails() +
            "}";
    }
}
