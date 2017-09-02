package com.sachin.microservice;

import com.sachin.services.RegistryService;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;

@Path("/register")
public class RegistryResource {

    RegistryService registryService = new RegistryService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogs()
    {
        Connection conn;
        try {
            conn = registryService.makeConnection();
            JSONArray resultJson = registryService.getUserLog(conn);
            registryService.closeConnection(conn);
            return Response.ok(resultJson.toString(), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
    }

    @GET
    @Path("reg/{param}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addLog(@PathParam("param") String msg) throws Exception{

        Connection conn = registryService.makeConnection();
        registryService.addLogToRegistry(conn, msg);
        registryService.closeConnection(conn);
        return "Success!";
    }
}

