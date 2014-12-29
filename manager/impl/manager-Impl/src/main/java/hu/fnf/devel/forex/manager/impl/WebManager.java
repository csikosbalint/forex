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
import org.apache.log4j.Logger;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnnym on 21/12/14.
 *
 * Implementation of Manager. This class will make available some control.
 */
public class WebManager implements Manager {

    private ConnectionFactory connectionFactory;

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void initMethod() {
        System.out.println("init manager");

        MessageListener messageListener = null;
        try {
            messageListener = new MessageListener(connectionFactory.createConnection());
        } catch (JMSException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(messageListener);
        thread.start();
    }

}

class MessageListener implements Runnable, ExceptionListener {
    private Map<String, MessageConsumer> consumers;
    private Logger logger = Logger.getLogger(MessageListener.class);


    public MessageListener(Connection connection) {
        try {
            System.out.println("create session");
            // Create the information (Topic or Queue)
            consumers = new HashMap<String, MessageConsumer>();
            Session session;
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            try {
                consumers.put("information", session.createConsumer(session.createQueue("information")));
                consumers.put("warnings", session.createConsumer(session.createQueue("warnings")));
                consumers.put("errors", session.createConsumer(session.createQueue("errors")));
            } catch (JMSException e) {
                e.printStackTrace();
            }
            connection.start();
            connection.setExceptionListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onException(JMSException e) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Object o : consumers.entrySet()) {
                    Map.Entry queueNameConsumer = (Map.Entry) o;
                    MessageConsumer queueConsumer = (MessageConsumer) queueNameConsumer.getValue();
                    String queueName = (String) queueNameConsumer.getKey();
                    Message message = queueConsumer.receive(1000);
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        processMessage(queueName, textMessage.getText());
                    }
                }
            } catch (Exception e) {
                // TODO: handle exceptions
            }
        }
    }

    private void processMessage(String queueName, String text) {
        logger.info(queueName + ": " + text);
    }
}

