package com.speechpro.android.session.session_library.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Alexander Grigal
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "is_active"
})
public class CheckSessionResponse {

    @JsonProperty("is_active")
    private Boolean isActive;

    /**
     * No args constructor for use in serialization
     *
     */
    public CheckSessionResponse() {
    }

    /**
     *
     * @param isActive
     */
    public CheckSessionResponse(Boolean isActive) {
        super();
        this.isActive = isActive;
    }

    @JsonProperty("is_active")
    public Boolean getIsActive() {
        return isActive;
    }

}

