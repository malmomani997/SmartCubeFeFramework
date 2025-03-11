package Base.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SCDTO {

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("newPassword")
    public String newPassword;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("familyName")
    public String familyName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("addressOne")
    public String addressOne;

    @JsonProperty("addressTwo")
    public String addressTwo;

    @JsonProperty("city")
    public String city;

    @JsonProperty("zip")
    public String zip;

    @JsonProperty("Country")
    public String country;

    @JsonProperty("languagePreference")
    public String languagePreference;

    @JsonProperty("favouriteCategory")
    public String favouriteCategory;

    @JsonProperty("state")
    public String state;

    @JsonProperty("phoneNumber")
    public String phoneNumber;
}
