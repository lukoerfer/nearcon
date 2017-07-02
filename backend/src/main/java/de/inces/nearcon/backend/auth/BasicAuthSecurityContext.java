package de.inces.nearcon.backend.auth;

import javax.ws.rs.core.SecurityContext;

public abstract class BasicAuthSecurityContext implements SecurityContext {

    private boolean secure;

    protected BasicAuthSecurityContext(boolean secure) {
        this.secure = secure;
    }

    @Override
    public boolean isSecure() {
        return this.secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
