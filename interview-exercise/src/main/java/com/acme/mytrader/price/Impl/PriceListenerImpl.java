package com.acme.mytrader.price.Impl;

import com.acme.mytrader.execution.Impl.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.strategy.TradingStrategy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PriceListenerImpl implements PriceListener {

    private double price;
    private String security;
    private TradingStrategy strategy;
    private ExecutionServiceImpl executionService = new ExecutionServiceImpl();

    // values which should be configuration ideally
    private static final int VOLUME = 100;
    private static final double THRESHOLD = 55.00;
    private static final String STOCK_TYPE = "IBM";

    private static Logger logger = Logger.getLogger(PriceListener.class.getName());

    public PriceListenerImpl(TradingStrategy strategy){
        this.strategy = strategy;
        strategy.addPriceListener(this);
    }

    @Override
    public void priceUpdate(String security, double price) {
        this.price = price;
        this.security = security;

        if(price < THRESHOLD && security.equalsIgnoreCase(STOCK_TYPE)){
            logger.log(Level.INFO, "Auto buying IBM stocks..");
            executionService.buy(security, price, VOLUME);
        }
    }
}
