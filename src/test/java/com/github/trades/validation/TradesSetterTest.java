
package com.github.trades.validation;

import com.github.trades.model.SetterResult;
import com.github.trades.model.Trade;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TradesSetterTest {
    TradesSetter setter = new TradesSetter();

    @Test
    public void isFieldValidTest(){
        assertFalse(setter.isFieldValid("potatoe"));
        assertTrue(setter.isFieldValid("tradeId"));
    }

    @Test
    public void getFieldNameFromSetterName(){
        assertEquals("name", setter.getFieldNameFromSetterName("setName"));
        assertEquals("potatoe", setter.getFieldNameFromSetterName("setPotatoe"));

        assertEquals("", setter.getFieldNameFromSetterName(null));
        assertEquals("", setter.getFieldNameFromSetterName(""));
        assertEquals("", setter.getFieldNameFromSetterName("set"));
    }

    @Test
    public void setFieldTest() throws Exception {
        Trade trade = new Trade();
        boolean success = setter.setField(trade, "tradeId", "thatId").
                isSuccessful();
        assertEquals("thatId", trade.getTradeId());
        assertTrue(success);

        success = setter.setField(trade, "tradeIdd", "thatId").
                isSuccessful();
        assertFalse(success);
    }

    @Test
    public void setTrade() throws Exception {
        Map.of("tradeId", "thatId");

        SetterResult result = setter.setTrade(Map.of("tradeId", "thatId"));
        assertTrue(result.isSuccessful());
        assertEquals("thatId", result.getTrade().getTradeId());

        result = setter.setTrade(Map.of("tradeIdddd", "thatId"));
        assertFalse(result.isSuccessful());
    }

}