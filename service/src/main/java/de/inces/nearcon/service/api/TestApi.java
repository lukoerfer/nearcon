package de.inces.nearcon.service.api;

import static javax.ws.rs.core.MediaType.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test")
public class TestApi {

    @GET
    @Produces(TEXT_PLAIN)
    public String start() {
        return "Test API";
    }
}
