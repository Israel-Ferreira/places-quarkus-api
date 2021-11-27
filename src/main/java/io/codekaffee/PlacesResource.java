package io.codekaffee;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.codekaffee.dto.PlaceDTO;
import io.codekaffee.exceptions.PlaceNotFoundException;
import io.codekaffee.models.Place;
import io.codekaffee.services.PlaceService;

@Path("/places")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class PlacesResource {

    private final PlaceService placeService;

    @Inject
    public PlacesResource(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GET
    public Response listAll() {
        var places = placeService.listAll();
        return Response.ok(places).build();
    }

    @GET
    @Path("{slug}")
    public Response getBySlug(@PathParam("slug") String slug) {
        try {
            Place place = placeService.findPlaceBySlug(slug);
            return Response.ok(place).build();
        } catch (PlaceNotFoundException nfex) {
            Map<String, String> errResponse = new HashMap<>();
            errResponse.put("message", nfex.getLocalizedMessage());
            errResponse.put("err.cod", "place.not.found.exception");

            return Response.status(Status.NOT_FOUND).entity(errResponse).build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    public Response createPlace(PlaceDTO placeDTO) {
        try {
            Place place = this.placeService.createPlace(placeDTO);
            return Response.status(Status.CREATED).entity(place).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{slug}")
    public Response deleteBySlug(@PathParam("slug") String slug) {

        try {
            this.placeService.deleteBySlug(slug);
            return Response.noContent().build();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
