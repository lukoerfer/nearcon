package de.inces.nearcon.backend.api.auth;

import static javax.ws.rs.core.MediaType.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import de.inces.nearcon.backend.persistence.EntityAccess;
import de.inces.nearcon.backend.persistence.PersistenceManager;
import de.inces.nearcon.core.model.auth.User;
import de.inces.nearcon.backend.Components;

@Path("/users")
@Produces(APPLICATION_JSON)
public class UserApi {

    private PersistenceManager persistenceManager;

    public UserApi() {
        this.persistenceManager = Components.get(PersistenceManager.class);
    }

    @POST
    public Response create() {
        try (EntityAccess access = persistenceManager.writable()) {
            User newUser = new User();
            access.persist(newUser);
            return Response.ok().entity(newUser).build();
        }
    }

    @GET
    public Response list() {
        try (EntityAccess access = persistenceManager.readable()) {
            return Response.ok().entity(access.findAll(User.class)).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        try (EntityAccess access = persistenceManager.readable()) {
            return Response.ok().entity(access.find(User.class, id)).build();
        }
    }

}
