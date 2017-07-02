package de.inces.nearcon.backend.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces(APPLICATION_JSON)
public class BaseApi {

    @GET
    public String base() {
        return "API";
    }

}
