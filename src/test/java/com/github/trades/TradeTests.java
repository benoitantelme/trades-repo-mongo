package com.github.trades;

import com.github.trades.model.Trade;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TradeTests {

    @Test
    public void tradeDateSetter() {
        Trade trade = new Trade();
        trade.setTradeDate("2019-10-26");

        assertNotNull(trade.getTradeDate());
    }

}
