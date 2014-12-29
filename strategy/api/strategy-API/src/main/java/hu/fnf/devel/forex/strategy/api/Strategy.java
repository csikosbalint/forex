package hu.fnf.devel.forex.strategy.api;

import com.dukascopy.api.IStrategy;
import com.dukascopy.api.feed.IFeedListener;
import org.osgi.framework.BundleActivator;

/**
 * Created by johnnym on 29/12/14.
 */
public interface Strategy extends IStrategy, IFeedListener, BundleActivator {
}
