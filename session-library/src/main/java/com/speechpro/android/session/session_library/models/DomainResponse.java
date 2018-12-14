package com.speechpro.android.session.session_library.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Alexander Grigal
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})
public class DomainResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public DomainResponse() {
    }

    /**
     * @param id
     * @param name
     */
    public DomainResponse(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

}
