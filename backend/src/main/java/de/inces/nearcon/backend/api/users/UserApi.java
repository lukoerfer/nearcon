package de.inces.nearcon.backend.api.users;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

import javax.annotation.security.RolesAllowed;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import de.inces.nearcon.backend.auth.Roles;
import de.inces.nearcon.backend.services.users.UserService;
import de.inces.nearcon.core.model.users.User;
import de.inces.nearcon.backend.Components;

@Path("/users")
@Produces(APPLICATION_JSON)
public class UserApi {

    // @Inject
    private UserService userService;

    public UserApi() {
        this.userService = Components.get(UserService.class);
    }

    @GET
    public List<User> list() {
        return this.userService.list();
    }

    @POST
    public User create() {
        return this.userService.create();
    }

    @GET
    @Path("/{id}")
    public User getById(@PathParam("id") long id) {
        return this.userService.get(id);
    }

}
