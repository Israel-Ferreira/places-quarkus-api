package io.codekaffee.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Validator;

import io.codekaffee.dto.PlaceDTO;
import io.codekaffee.exceptions.PlaceNotFoundException;
import io.codekaffee.models.Place;
import io.codekaffee.repository.PlaceRepository;

@ApplicationScoped
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final Validator validator;

    @Inject
    public PlaceService(PlaceRepository placeRepository, Validator validator) {
        this.placeRepository = placeRepository;
        this.validator = validator;
    }

    @Transactional
    public Place createPlace(PlaceDTO placeDTO) {
        Place place = new Place(placeDTO);

        var violations = validator.validate(placeDTO);

        if (!violations.isEmpty()) {
            throw new RuntimeException(Arrays.toString(violations.toArray()));
        }

        placeRepository.persist(place);

        return place;
    }

    public Place findPlaceBySlug(String slug) {
        Optional<Place> placeOpt = this.placeRepository.findBySlug(slug);

        if (placeOpt.isEmpty()) {
            throw new PlaceNotFoundException();
        }

        return placeOpt.get();
    }

    @Transactional
    public void deleteBySlug(String slug) {

        try {
            this.placeRepository.delete("slug", slug);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    public List<Place> listAll() {
        return placeRepository.findAll().list();
    }

}
