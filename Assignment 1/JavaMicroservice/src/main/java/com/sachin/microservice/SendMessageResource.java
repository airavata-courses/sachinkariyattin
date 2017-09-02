package com.sachin.microservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/sendmessage")
public class SendMessageResource {

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) throws Exception{

        String content = "";
        String newmsg = msg + "AppendedbyJavaMicroservice";
        System.out.println(newmsg);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8080/JavaMicroservice/webapi/register/reg/"+newmsg);
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            content = EntityUtils.toString(entity);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(content).build();
    }
}
