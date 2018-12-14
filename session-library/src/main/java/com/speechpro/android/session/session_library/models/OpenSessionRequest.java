package com.speechpro.android.session.session_library.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Alexander Grigal
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "password",
        "domain_id"

})
public class OpenSessionRequest {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("domain_id")
    private Integer domainId;

    /**
     * No args constructor for use in serialization
     *
     */
    public OpenSessionRequest() {
    }

    /**
     *
     * @param username
     * @param password
     * @param domainId
     */
    public OpenSessionRequest(String username, String password, Integer domainId) {
        super();
        this.username = username;
        this.password = password;
        this.domainId = domainId;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("domain_id")
    public Integer getDomainId() {
        return domainId;
    }

}