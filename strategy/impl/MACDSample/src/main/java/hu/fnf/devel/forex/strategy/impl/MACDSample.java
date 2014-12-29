package hu.fnf.devel.forex.strategy.impl;

import com.dukascopy.api.*;
import com.dukascopy.api.feed.IFeedDescriptor;
import hu.fnf.devel.forex.strategy.api.Strategy;
import org.osgi.framework.BundleContext;

/**
 * Created by johnnym on 29/12/14.
 */
public class MACDSample implements Strategy {
    @Override
    public void start(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void onStart(IContext iContext) throws JFException {

    }

    @Override
    public void onTick(Instrument instrument, ITick iTick) throws JFException {

    }

    @Override
    public void onBar(Instrument instrument, Period period, IBar iBar, IBar iBar1) throws JFException {

    }

    @Override
    public void onMessage(IMessage iMessage) throws JFException {

    }

    @Override
    public void onAccount(IAccount iAccount) throws JFException {

    }

    @Override
    public void onStop() throws JFException {

    }

    @Override
    public void onFeedData(IFeedDescriptor feedDescriptor, ITimedData feedData) {

    }
}
