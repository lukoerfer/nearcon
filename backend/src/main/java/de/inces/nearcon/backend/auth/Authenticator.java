package de.inces.nearcon.backend.auth;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import de.inces.nearcon.backend.Components;
import de.inces.nearcon.backend.persistence.EntityAccess;
import de.inces.nearcon.backend.persistence.PersistenceManager;
import de.inces.nearcon.core.model.auth.User;
import lombok.Synchronized;

public class Authenticator implements ContainerRequestFilter {

    private PersistenceManager persistenceManager;

    public Authenticator() {
        this.persistenceManager = Components.get(PersistenceManager.class);
    }

    @Context
    private HttpServletRequest request;

    @Synchronized
    @Override
    public void filter(ContainerRequestContext context) {
        // Extract credentials
        String authHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        Credentials credentials = HttpBasicAuth.extract(authHeader);
        if (credentials == null) return;
        try (EntityAccess access = this.persistenceManager.readable()) {
            User currentUser = access.find(User.class, credentials.getUsername());
            if (currentUser == null) return;
            if (currentUser.getSecret().equals(credentials.getPassword())) {
                context.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return currentUser;
                    }
                    @Override
                    public boolean isUserInRole(String role) {
                        return "User".equals(role);
                    }
                    @Override
                    public boolean isSecure() {
                        return request.isSecure();
                    }
                    @Override
                    public String getAuthenticationScheme() {
                        return SecurityContext.BASIC_AUTH;
                    }
                });
            }
        }
    }

}
