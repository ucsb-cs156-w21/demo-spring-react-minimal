package edu.ucsb.changeme.models;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class GoogleUserProfile {

  private static final Logger logger = LoggerFactory.getLogger(GoogleUserProfile.class);
  private String sub;
  private String name;
  private String email;
  @JsonProperty("given_name")
  private String givenName;
  @JsonProperty("family_name")
  private String familyName;
  private String nickname;
  private String picture;
  private String locale;
  @JsonProperty("updated_at")
  private String updatedAt;

  /**
   * @param json - the json string of the object being created
   * @return GoogleUserProfile (nullable)
   */
  public static GoogleUserProfile fromJSON(String json) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      GoogleUserProfile userProfile = objectMapper.readValue(json, GoogleUserProfile.class);
      return userProfile;
    } catch (JsonProcessingException jpe) {
      logger.error("JsonProcessingException:" + jpe);
      return null;
    }
  }

  public String toJSONString() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }
}
