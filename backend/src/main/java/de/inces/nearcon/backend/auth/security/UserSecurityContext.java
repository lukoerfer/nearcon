package de.inces.nearcon.backend.auth.security;

import java.security.Principal;

import de.inces.nearcon.backend.auth.BasicAuthSecurityContext;
import de.inces.nearcon.backend.auth.Roles;
import de.inces.nearcon.core.model.users.User;

public class UserSecurityContext extends BasicAuthSecurityContext {

    private User user;

    public UserSecurityContext(User user, boolean secure) {
        super(secure);
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> this.user.toString();
    }

    @Override
    public boolean isUserInRole(String role) {
        return Roles.User.equals(role);
    }
}
