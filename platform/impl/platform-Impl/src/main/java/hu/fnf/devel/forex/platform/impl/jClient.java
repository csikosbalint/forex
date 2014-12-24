/*
 * The hu.fnf.devel.forex.platform.impl.jClient
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

package hu.fnf.devel.forex.platform.impl;


import com.dukascopy.api.impl.connect.DCClientImpl;
import com.dukascopy.api.system.IClient;
import com.dukascopy.api.system.ISystemListener;
import hu.fnf.devel.forex.manager.api.Manager;
import hu.fnf.devel.forex.platform.api.Platform;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.jms.ConnectionFactory;
import java.util.Observable;

/**
 * Created by johnnym on 08/12/14.
 * This service is intended to provide the forex platform service.
 */

public class jClient extends Observable implements Platform, BundleActivator {

    private ConnectionFactory testMQ;

    public void setTestMQ(ConnectionFactory testMQ) {
        this.testMQ = testMQ;
    }

    public void setManager(Manager manager) {
        addObserver(manager);
    }

    /**
     * This method is intended to be called if the init-method of the bean is defined.
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public void initBundle() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        System.out.println("init");
        Logger logger = Logger.getLogger(jClient.class);
        IClient iClient = new DCClientImpl();
        iClient.setSystemListener(new ISystemListener() {
            @Override
            public void onStart(long processId) {

            }

            @Override
            public void onStop(long processId) {

            }

            @Override
            public void onConnect() {

            }

            @Override
            public void onDisconnect() {

            }
        });
//        try {
//            // Create a Connection
//            Connection connection = testMQ.createConnection("admin","admin");
//            connection.start();
//
//            // Create a Session
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//            // Create the destination (Topic or Queue)
//            Destination destination = session.createQueue("TEST.FOO");
//
//            // Create a MessageProducer from the Session to the Topic or Queue
//            MessageProducer producer = session.createProducer(destination);
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//            // Create a messages
//            String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
//            TextMessage message = session.createTextMessage(text);
//
//            // Tell the producer to send the message
//            System.out.println("Sent message: "+ message.hashCode() + " : " + Thread.currentThread().getName());
//            producer.send(message);
//
//            // Clean up
//            session.close();
//            connection.close();
//        }
//        catch (Exception e) {
//            System.out.println("Caught: " + e);
//            e.printStackTrace();
//        }
        setChanged();
        notifyObservers("platform bundle init");
    }

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("start");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("stop");
    }
}
