/*
 * The hu.fnf.devel.forex.manager.impl.WebManager
 * is part of the forex project.
 * Copyright (C) 2014 johnnym
 *
 *     This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or any later
 * version.
 *
 *     This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 *     You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */

package hu.fnf.devel.forex.manager.impl;

import hu.fnf.devel.forex.manager.api.Manager;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Observable;

/**
 * Created by johnnym on 21/12/14.
 */
public class WebManager implements Manager, ExceptionListener {

    private ConnectionFactory testMQ;

    public void setTestMQ(ConnectionFactory testMQ) {
        this.testMQ = testMQ;
    }

    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public void initMethod() {

        System.out.println("init manager");
        try {
            // Create a Connection
//            Connection connection = testMQ.createConnection();
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection("karaf", "karaf");
            connection.start();


            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("TEST.QUEUE");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String resp = (String) arg;
            System.out.println("\nReceived Response: " + resp);
        }

    }


    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}

