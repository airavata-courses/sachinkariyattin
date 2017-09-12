package com.sachin.microservice;

import com.rabbitmq.client.*;
import com.sachin.services.RabbitSendReceive;
import com.sachin.services.RegistryService;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Connection;

@Path("/register")
public class RegistryResource {

    RegistryService registryService = new RegistryService();
    RabbitSendReceive rabbitSendReceive = new RabbitSendReceive();

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
        com.rabbitmq.client.Connection connection = rabbitSendReceive.makeConnectionChannel();
        Channel channel = connection.createChannel();
        channel.queueDeclare("QUEUE2", false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    Connection conn = registryService.makeConnection();
                    registryService.addLogToRegistry(conn, message);
                    registryService.closeConnection(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume("QUEUE2", true, consumer);
        return "Success!";
    }
}

