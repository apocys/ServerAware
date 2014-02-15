
package com.aware.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    private final String action;
    private final String data;

    private Request(String action, String data) {
        this.action = action;
        this.data = data;
    }

    @JsonProperty
    public String getAction() {
        return action;
    }

    @JsonProperty
    public String getData() {
        return data;
    }

  
}
