package de.inces.nearcon.backend.api.events;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.inces.nearcon.core.model.events.EventCategory;

@Path("/events/categories")
@Produces(APPLICATION_JSON)
public class EventCategoryApi {

    @GET
    public List<EventCategory> list() {
        List<EventCategory> categories = new ArrayList<>();
        categories.add(new EventCategory("Meet and Eat"));
        categories.add(new EventCategory("Party hard!"));
        categories.add(new EventCategory("Run and Fun"));
        return categories;
    }

}
