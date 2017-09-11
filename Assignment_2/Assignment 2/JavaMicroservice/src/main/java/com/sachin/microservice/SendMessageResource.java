package com.sachin.microservice;

import com.rabbitmq.client.*;
import com.sachin.services.RabbitSendReceive;
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

    RabbitSendReceive rabbitSendReceive = new RabbitSendReceive();

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) throws Exception{

        String content = "";
        String newmsg = msg + "AppendedbyJavaMicroservice";
        System.out.println(newmsg);

        Connection connection = rabbitSendReceive.makeConnectionChannel();
        receiveMessage(connection);

        return Response.status(200).entity(content).build();
    }

    public void receiveMessage(final Connection connection) throws Exception{

        Channel channel = connection.createChannel();
        channel.queueDeclare("QUEUE1", false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                     rabbitSendReceive.sendMessage(message + "AppendedbyJavaMicroservice", connection, "QUEUE2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume("QUEUE1", true, consumer);
    }
}
