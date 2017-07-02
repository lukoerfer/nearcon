package de.inces.nearcon.backend.auth;

import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import de.inces.nearcon.backend.Components;
import de.inces.nearcon.backend.model.Credentials;
import de.inces.nearcon.backend.services.users.UserService;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

public class Authenticator implements ContainerRequestFilter {

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String ANONYMOUS = "ANONYMOUS";

    private UserService userService;

    public Authenticator() {
        this.userService = Components.get(UserService.class);
    }

    @Override
    public void filter(ContainerRequestContext context) {
        // Check for secure connection
        boolean secure = context.getSecurityContext().isSecure();
        // Extract credentials
        String authHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        Credentials credentials = BasicAuthentication.extract(authHeader);
        // Handle credentials
        if (credentials != null) {
            SecurityContext security = buildSecurityContext(secure, credentials);
            if (security != null) {
                context.setSecurityContext(security);
            } else {
                context.abortWith(Response.status(UNAUTHORIZED).build());
            }
        } else {
            context.abortWith(Response.status(UNAUTHORIZED).build());
        }
    }

    private SecurityContext buildSecurityContext(boolean secure, Credentials credentials) {
        return null;
    }

}
