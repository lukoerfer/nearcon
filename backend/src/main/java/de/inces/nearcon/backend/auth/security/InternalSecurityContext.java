package de.inces.nearcon.backend.auth.security;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import de.inces.nearcon.backend.auth.BasicAuthSecurityContext;

public class InternalSecurityContext extends BasicAuthSecurityContext {

    private List<String> roles;

    public InternalSecurityContext(boolean secure, String... roles) {
        super(secure);
        this.roles = Arrays.asList(roles);
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> String.join("|", this.roles);
    }

    @Override
    public boolean isUserInRole(String role) {
        return this.roles.contains(role);
    }
}
