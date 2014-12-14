package hu.fnf.devel.forex.strategy;

import com.dukascopy.api.impl.connect.DCClientImpl;
import com.dukascopy.api.system.IClient;
import com.dukascopy.api.system.ISystemListener;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Created by johnnym on 08/12/14.
 * This service is intended to provide the forex platform service.
 */

public class Platform implements BundleActivator {

        /**
         * This method is intended to be called if the init-method of the bean is defined.
         *
         * @throws IllegalAccessException
         * @throws InstantiationException
         * @throws ClassNotFoundException
         */
        public void initBundle() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
                Logger logger = Logger.getLogger( Platform.class );
                IClient iClient = new DCClientImpl();

                iClient.setSystemListener( new ISystemListener() {
                        @Override public void onStart( long processId ) {

                        }

                        @Override public void onStop( long processId ) {

                        }

                        @Override public void onConnect() {

                        }

                        @Override public void onDisconnect() {

                        }
                } );
                System.out.println( "init bundle" );
                logger.info( "init: " + iClient.toString() );
        }

        @Override public void start( BundleContext context ) throws Exception {
                System.out.println( "start bundle" );
        }

        @Override public void stop( BundleContext context ) throws Exception {
                System.out.println( "stop bundle" );
        }
}
