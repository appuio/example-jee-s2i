package ch.appuio.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

/**
 * A simple REST service
 */

@Path("/")
public class HelloWorld {
    @Inject
    HelloService helloService;

    @Inject
    ApplicationVersionService applicationVersionService;

    @POST
    @Path("/json/{name}")
    @Produces("application/json")
    public String getHelloWorldJSON(@PathParam("name") String name) {
        return "{\"result\":\"" + helloService.createHelloMessage(name) + "\"}";
    }

    @POST
    @Path("/xml/{name}")
    @Produces("application/xml")
    public String getHelloWorldXML(@PathParam("name") String name) {
        return "<xml><result>" + helloService.createHelloMessage(name) + "</result></xml>";
    }
    
    @GET
    @Path("/json/env")
    @Produces("application/json")
    public String getJavaEnvironment(){
    	Gson g = new Gson();
    	return g.toJson(System.getenv());
    }
    
    @GET
    @Path("/json/hellos")
    @Produces("application/json")
    public String getHellos(){
    	Gson g = new Gson();
    	return g.toJson(helloService.getAllHellos());
    }

    @GET
    @Path("/appuio")
    @Produces("application/json")
    public String getAppuio(){
        Gson g = new Gson();
        return g.toJson("Version: " + applicationVersionService.getApplicationBuildInfo().getVersion().getValue() + " POD: "+ getPodName());
    }

    private String getPodName(){
        return System.getenv("HOSTNAME");
    }
}
