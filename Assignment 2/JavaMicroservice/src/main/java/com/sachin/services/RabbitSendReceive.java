package com.sachin.services;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitSendReceive {

    public Connection makeConnectionChannel() throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("my-rabbit");
        Connection connection = factory.newConnection();

        return connection;
    }

    public void sendMessage(String message, Connection connection, String queueName) throws Exception {

        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();

    }

}
