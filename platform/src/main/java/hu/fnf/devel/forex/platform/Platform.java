package hu.fnf.devel.forex.platform;

import com.dukascopy.api.system.ClientFactory;
import com.dukascopy.api.system.IClient;
import com.dukascopy.api.system.ISystemListener;
import org.apache.log4j.Logger;

/**
 * Created by johnnym on 08/12/14.
 */
public class Platform {
        private Logger logger = Logger.getLogger( Platform.class );

        public void initBundle() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
                IClient iClient = ClientFactory.getDefaultInstance();
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
        }

}
