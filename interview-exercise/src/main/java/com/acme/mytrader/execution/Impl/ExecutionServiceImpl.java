package com.acme.mytrader.execution.Impl;

import com.acme.mytrader.execution.ExecutionService;

public class ExecutionServiceImpl implements ExecutionService {

    @Override
    public void buy(String security, double price, int volume) {
        //Add code here to buy stocks calling a repository etc.
        System.out.println("The item " +security+" is purchased for: " +
                price + " volume: " + volume);
    }

    @Override
    public void sell(String security, double price, int volume) {
        //Add code here to sell stocks calling a repository etc.
        System.out.println("The item " +security+" is sold for: " +
                price + " volume: " + volume);
    }
}
