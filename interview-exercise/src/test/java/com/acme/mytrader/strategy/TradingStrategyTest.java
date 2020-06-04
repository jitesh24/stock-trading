package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.execution.Impl.ExecutionServiceImpl;
import com.acme.mytrader.price.Impl.PriceListenerImpl;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TradingStrategyTest {

    @Test
    public void testRunApp(){
        TradingStrategy strategy = new TradingStrategy();
        PriceListenerImpl priceListener = new PriceListenerImpl(strategy);
        strategy.priceChange("IBM", 52.00);
    }

    @Test
    public void testWhenGiveStockTypeIsInvalid() {
        TradingStrategy strategy = new TradingStrategy();
        PriceListenerImpl priceListener = mock(PriceListenerImpl.class);
        ExecutionService service = mock(ExecutionServiceImpl.class);
        strategy.addPriceListener(priceListener);
        strategy.priceChange(" ", 53.0);
        verify(priceListener, Mockito.times(1))
                .priceUpdate(" ", 53.0);
        verify(service, Mockito.never()).buy(" ", 53.0, 100);
    }

    @Test
    public void testWhenValidStockTypeAndPriceUpdates() {
        TradingStrategy strategy = new TradingStrategy();
        PriceListenerImpl priceListener = mock(PriceListenerImpl.class);
        strategy.addPriceListener(priceListener);
        strategy.priceChange("IBM", 52.0);
        verify(priceListener, Mockito.times(1))
                .priceUpdate("IBM", 52.0);
    }

    @Test
    public void testWhenGivePriceIsInvalid() {
        TradingStrategy strategy = new TradingStrategy();
        PriceListenerImpl priceListener = mock(PriceListenerImpl.class);
        strategy.addPriceListener(priceListener);
        strategy.priceChange("IBM", 0.0);
        verify(priceListener).priceUpdate("IBM", 0.0);
    }

    @Test
    public void testWhenGiveStockPriceIsBelowThreshold() {
        TradingStrategy strategy = mock(TradingStrategy.class);
        PriceListenerImpl priceListener = new PriceListenerImpl(strategy);
        strategy.priceChange("IBM", 52.00);
        verify(strategy).addPriceListener(priceListener);
        verify(strategy, never()).removePriceListener(priceListener);
    }

    @Test
    public void testWhenGiveStockPriceIsAboveThreshold() {
        TradingStrategy strategy = mock(TradingStrategy.class);
        PriceListenerImpl priceListener = new PriceListenerImpl(strategy);
        strategy.priceChange("IBM", 59.00);
        verify(strategy).addPriceListener(priceListener);
        verify(strategy, never()).removePriceListener(priceListener);
        verify(strategy, never()).notifyListener();
    }

    @Test
    public void testWhenGiveStockTypeIsDifferent() {
        TradingStrategy strategy = mock(TradingStrategy.class);
        PriceListenerImpl priceListener = new PriceListenerImpl(strategy);
        strategy.priceChange("GOOGL", 53.00);
        verify(strategy).addPriceListener(priceListener);
        verify(strategy, never()).removePriceListener(priceListener);
        verify(strategy, never()).notifyListener();
    }
}
