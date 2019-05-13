package com.github.trades.validation;

import com.github.trades.Trade;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TradesSetterTest {
    @Test
    public void isFieldValidTest(){
        assertFalse(TradesSetter.isFieldValid("potatoe"));
        assertTrue(TradesSetter.isFieldValid("tradeId"));
    }

    @Test
    public void getFieldNameFromSetterName(){
        assertEquals("name", TradesSetter.getFieldNameFromSetterName("setName"));
        assertEquals("potatoe", TradesSetter.getFieldNameFromSetterName("setPotatoe"));

        assertEquals("", TradesSetter.getFieldNameFromSetterName(null));
        assertEquals("", TradesSetter.getFieldNameFromSetterName(""));
        assertEquals("", TradesSetter.getFieldNameFromSetterName("set"));
    }

    @Test
    public void setFieldTest() throws Exception {
        Trade trade = new Trade();
        boolean result = TradesSetter.setField(trade, "tradeId", "thatId");
        assertEquals("thatId", trade.getTradeId());
        assertTrue(result);

        result = TradesSetter.setField(trade, "tradeIdd", "thatId");
        assertFalse(result);
    }



}
