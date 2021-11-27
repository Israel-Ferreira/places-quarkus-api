package io.codekaffee;

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
import io.codekaffee.models.Place;
import io.codekaffee.services.PlaceService;

@Path("/places")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class PlacesResource {

   
    private final PlaceService placeService;

    @Inject
    public PlacesResource(PlaceService placeService){
        this.placeService = placeService;
    }
    
    @GET
    public Response listAll() {
        var places = placeService.listAll();
        return Response.ok(places).build();
    }


    @GET
    @Path("{slug}")
    public Response getBySlug(@PathParam("slug") String slug){

        System.out.println(slug);
        return Response.ok().build();
    }


    @POST
    public Response createPlace(PlaceDTO placeDTO){
        try {
            Place place = this.placeService.createPlace(placeDTO);
            return Response.status(Status.CREATED).entity(place).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @DELETE
    @Path("{slug}")
    public Response deleteBySlug(@PathParam("slug") String slug){
        System.out.println(slug);
        return Response.noContent().build();
    }
}