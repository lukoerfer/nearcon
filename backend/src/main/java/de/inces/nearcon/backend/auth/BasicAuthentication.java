package de.inces.nearcon.backend.auth;

import java.util.Base64;

import javax.ws.rs.core.SecurityContext;

import de.inces.nearcon.backend.model.Credentials;
import de.inces.nearcon.core.util.Strings;

public class BasicAuthentication {

    public static Credentials extract(String authHeader) {
        if (authHeader != null) {
            String[] parts = authHeader.split(Strings.Space.$(), 2);
            if (!ensureMethod(parts)) return null;
            String decoded = new String(Base64.getDecoder().decode(parts[1]));
            return buildCredentials(decoded);
        }
        return null;
    }

    private static boolean ensureMethod(String[] parts) {
        return parts.length == 2
            && parts[0].equalsIgnoreCase(SecurityContext.BASIC_AUTH);
    }

    private static Credentials buildCredentials(String decoded) {
        String[] parts = decoded.split(Strings.Colon.$(), 2);
        if (parts.length == 2) {
            return new Credentials(parts[0], parts[1]);
        }
        return null;
    }

}
