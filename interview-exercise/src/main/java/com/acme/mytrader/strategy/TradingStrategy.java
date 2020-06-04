package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

import java.util.ArrayList;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy implements PriceSource {

    private ArrayList<PriceListener> priceListeners;//Arraylist because we can add additional listeners
    private double price;
    private String security;

    public TradingStrategy(){
        priceListeners = new ArrayList<>();
    }

    public void priceChange(String stockName, double newPrice){
        this.price = newPrice;
        this.security = stockName;
        notifyListener();
    }

    @Override
    public void addPriceListener(PriceListener listener) {
        priceListeners.add(listener);
    }

    @Override
    public void removePriceListener(PriceListener listener) {
        priceListeners.remove(priceListeners.indexOf(listener));
    }

    @Override
    public void notifyListener() {
        for (PriceListener pl : priceListeners) {
            pl.priceUpdate(security, price);
        }
    }
}
