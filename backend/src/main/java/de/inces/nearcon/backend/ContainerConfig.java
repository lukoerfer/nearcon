package de.inces.nearcon.backend;

import org.glassfish.jersey.server.ResourceConfig;

import de.inces.nearcon.backend.api.BaseApi;
import de.inces.nearcon.backend.auth.Authenticator;

public class ContainerConfig extends ResourceConfig {

    public ContainerConfig() {
        // Register API packages
        this.packages(true, BaseApi.class.getPackage().getName());
        // Register the authentication filter
        this.register(Authenticator.class);
    }

}
