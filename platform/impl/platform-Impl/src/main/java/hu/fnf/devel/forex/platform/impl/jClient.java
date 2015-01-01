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


import com.dukascopy.api.Instrument;
import com.dukascopy.api.impl.connect.DCClientImpl;
import com.dukascopy.api.system.IClient;
import com.dukascopy.api.system.ISystemListener;
import hu.fnf.devel.forex.platform.api.Platform;
import hu.fnf.devel.forex.strategy.impl.MACDSample;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.jms.ConnectionFactory;
import java.util.HashSet;

/**
 * Created by johnnym on 08/12/14.
 * This service is intended to provide the forex platform service.
 */

public class jClient implements Platform, BundleActivator {

    private ConnectionFactory connectionFactory;

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void initMethod() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        System.out.println("init platform");
        try {
            setConnectionFactory(new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
        IClient iClient = new DCClientImpl();
        iClient.setSystemListener(new ISystemListener() {
            @Override
            public void onStart(long processId) {
                sendMessage("information", "client is started");

            }

            @Override
            public void onStop(long processId) {
                sendMessage("information", "client is stopped");

            }

            @Override
            public void onConnect() {
                sendMessage("information", "client is connected");

            }

            @Override
            public void onDisconnect() {
                sendMessage("information", "client is disconnected");

            }
        });
        try {
            iClient.connect("https://eu-demo.dukascopy.com/fo/platform/jForex", "DEMO2mPMgP", "mPMgP");
        } catch (Exception e) {
            e.printStackTrace();
        }
// wait for it to connect
        int i = 50; // wait max ten seconds
        while (i > 0 && !iClient.isConnected()) {
            try {
                sendMessage("information", "client is connecting...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                sendMessage("error", "client connection is interrupted");
                return;
            }
            i--;
        }
        if (iClient.isConnected()) {
            HashSet<Instrument> instrumentSet = new HashSet<Instrument>();
            instrumentSet.add(Instrument.EURUSD);
            instrumentSet.add(Instrument.USDJPY);
            instrumentSet.add(Instrument.EURJPY);
            instrumentSet.add(Instrument.NZDJPY);
            iClient.setSubscribedInstruments(instrumentSet);
            iClient.startStrategy(new MACDSample());
        }

    }

    private void sendMessage(String queue, String msg) {
        try {
            System.out.println(queue + ": " + msg);
            // Create a Connection
//            Connection connection = connectionFactory.createConnection("karaf","karaf");
//            connection.start();
//
//            // Create a Session
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//            // Create the destination (Topic or Queue)
//            Destination destination = session.createQueue(queue);
//
//            // Create a MessageProducer from the Session to the Topic or Queue
//            MessageProducer producer = session.createProducer(destination);
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//            // Create a messages
//            TextMessage message = session.createTextMessage(msg);
//
//            // Tell the producer to send the message
//            producer.send(message);
//
//            // Clean up
//            session.close();
//            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
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
