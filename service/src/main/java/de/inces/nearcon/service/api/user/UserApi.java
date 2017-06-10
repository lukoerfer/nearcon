package de.inces.nearcon.service.api.user;

import static javax.ws.rs.core.MediaType.*;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import de.inces.nearcon.core.model.users.User;
import de.inces.nearcon.service.persistence.EntityAccess;
import de.inces.nearcon.service.persistence.PersistenceManager;

@Path("/users")
public class UserApi {

    private PersistenceManager persistenceManager;

    @POST
    @Produces(APPLICATION_JSON)
    public User create() {
        User user = new User();
        return user;
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public User getById(@PathParam("id") long id) {
        return new User();
    }

}
