package com.acme.mytrader.price.Impl;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class PriceListenerImplTest {

    @Test
    public void testPriceUpdateForHigherThreshold(){
        TradingStrategy strategy = mock(TradingStrategy.class);
        ExecutionService executionService = mock(ExecutionService.class);
        PriceListenerImpl priceListener = new PriceListenerImpl(strategy);
        priceListener.priceUpdate("IBM", 59.00);
        verify(executionService, never()).buy("IBM", 59.00, 100);
    }

    @Test
    public void testPriceUpdateForDifferentStock(){
        TradingStrategy strategy = mock(TradingStrategy.class);
        ExecutionService executionService = mock(ExecutionService.class);
        PriceListenerImpl priceListener = new PriceListenerImpl(strategy);
        priceListener.priceUpdate("APPL", 55.00);
        verify(executionService, never()).buy("APPL", 55.00, 100);
    }

}