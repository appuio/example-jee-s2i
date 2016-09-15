package ch.appuio.example;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * A simple REST service which returns the health status of the Application as json
 */

@Path("/")
public class HealthCheck {
    @Inject
    HelloService helloService;

    @Inject
    ApplicationVersionService applicationVersionService;

    @GET
    @Path("/health")
    @Produces("application/json")
    public String getHealth() {

        // Implement complex Check
        Gson g = new Gson();
        g.toJson("App is Ready");
        return g.toJson("App is Ready");
    }

}
