package com.speechpro.android.session.session_library.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Alexander Grigal
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "session_id"
})
public class SessionIdResponse {

    @JsonProperty("session_id")
    private String sessionId;

    /**
     * No args constructor for use in serialization
     *
     */
    public SessionIdResponse() {
    }

    /**
     *
     * @param sessionId
     */
    public SessionIdResponse(String sessionId) {
        super();
        this.sessionId = sessionId;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

}
