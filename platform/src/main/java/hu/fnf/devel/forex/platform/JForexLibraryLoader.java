package hu.fnf.devel.forex.platform;

import org.apache.log4j.Logger;

/**
 * Created by johnnym on 08/12/14.
 * This service is intended to provide the forex platform service.
 */

public class JForexLibraryLoader {

        /**
         * This method is intended to be called if the init-method of the bean is defined.
         *
         * @throws IllegalAccessException
         * @throws InstantiationException
         * @throws ClassNotFoundException
         */
        public void initBundle() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
                Logger logger = Logger.getLogger( JForexLibraryLoader.class );
                //                IClient iClient = ClientFactory.getDefaultInstance();
                //                                iClient.setSystemListener( new ISystemListener() {
                //                                        @Override public void onStart( long processId ) {
                //
                //                                        }
                //
                //                                        @Override public void onStop( long processId ) {
                //
                //                                        }
                //
                //                                        @Override public void onConnect() {
                //
                //                                        }
                //
                //                                        @Override public void onDisconnect() {
                //
                //                                        }
                //                                } );
                System.out.println( "init libs" );
                //                logger.info( "init: " + iClient.getPreferences().toString() );
        }

}
