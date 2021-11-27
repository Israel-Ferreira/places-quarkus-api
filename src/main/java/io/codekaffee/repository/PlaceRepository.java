package io.codekaffee.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.codekaffee.models.Place;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PlaceRepository implements PanacheRepository<Place> {

    public Optional<Place> findBySlug(String slug){
        return find("slug", slug).firstResultOptional();
    }

    public List<Place> findPlacesByCity(String city){
        return find("city", city).list();
    }

    
    
}
