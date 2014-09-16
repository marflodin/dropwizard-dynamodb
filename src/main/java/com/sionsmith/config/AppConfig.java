package com.sionsmith.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by sionsmith on 12/09/2014.
 */
public class AppConfig extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}
