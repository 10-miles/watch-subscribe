package com.subscribe.watch.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUser implements Serializable {

  @JsonProperty("email")
  private String email;

  @JsonProperty("name")
  private String name;

  @JsonProperty("picture")
  private String picture;

}