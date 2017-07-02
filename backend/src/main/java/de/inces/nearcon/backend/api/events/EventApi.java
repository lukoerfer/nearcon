package de.inces.nearcon.backend.api.events;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import de.inces.nearcon.core.model.events.Event;

import static javax.ws.rs.core.MediaType.*;

@Path("/events")
@Produces(APPLICATION_JSON)
public class EventApi {

    @GET
    public List<Event> list() {
        return null;
    }

    @POST
    public Event create(Event event) {
        return null;
    }

    @GET
    @Path("/near")
    public List<Event> near(@QueryParam("lat") double latitude, @QueryParam("lng") double longitude) {
        return null;
    }

}
