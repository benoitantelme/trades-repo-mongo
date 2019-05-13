package com.github.trades;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TradesTests {

    @Test
    public void tradeDateSetter() {
        Trades trade = new Trades();
        trade.setTradeDate("2019-10-26");

        assertNotNull(trade.getTradeDate());
    }

}
