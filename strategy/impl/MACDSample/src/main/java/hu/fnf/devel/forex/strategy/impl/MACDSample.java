package hu.fnf.devel.forex.strategy.impl;

import com.dukascopy.api.*;
import hu.fnf.devel.forex.strategy.api.Strategy;
import org.osgi.framework.BundleContext;

/**
 * Created by johnnym on 29/12/14.
 */
public class MACDSample implements Strategy {

    private IEngine engine;
    private IConsole console;
    private IHistory history;
    private IContext context;
    private IIndicators indicators;
    private IUserInterface userInterface;

    @Override
    public void onStart(IContext context) throws JFException {
        System.out.println("strategy started");
        this.engine = context.getEngine();
        this.console = context.getConsole();
        this.history = context.getHistory();
        this.context = context;
        this.indicators = context.getIndicators();
        this.userInterface = context.getUserInterface();
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }

    @Override
    public void onTick(Instrument instrument, ITick iTick) throws JFException {
        System.out.println("tick: " + instrument.name() + "/" + iTick.getAsk());
    }

    @Override
    public void onBar(Instrument instrument, Period period, IBar iBar, IBar iBar1) throws JFException {

    }

    @Override
    public void onMessage(IMessage iMessage) throws JFException {
        System.out.println(iMessage.getContent());

    }

    @Override
    public void onAccount(IAccount iAccount) throws JFException {
        System.out.println(iAccount.getAccountState());
    }

    @Override
    public void onStop() throws JFException {

    }
}
