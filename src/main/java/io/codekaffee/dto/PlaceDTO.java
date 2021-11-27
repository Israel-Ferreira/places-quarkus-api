package io.codekaffee.dto;

import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;

public class PlaceDTO {

    @NotNull
    @NotBlank
    private String name;


    @NotNull
    @NotBlank
    private String slug;

    @NotBlank
    private String city;

    private String state;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
}
