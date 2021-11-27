package io.codekaffee.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.codekaffee.dto.PlaceDTO;


@Entity
public class Place  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    private String city;
    private String state;


    public Place(){}


    public Place(PlaceDTO placeDTO){
        this.city = placeDTO.getCity();
        this.name = placeDTO.getName();
        this.slug = placeDTO.getSlug();
        this.state = placeDTO.getState();
    }


    public Place(Long id, String name, String slug, String city, String state) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.city = city;
        this.state = state;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    


}
