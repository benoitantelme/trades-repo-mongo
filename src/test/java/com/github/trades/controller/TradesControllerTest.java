package com.github.trades.controller;


import com.github.trades.model.Trade;
import com.github.trades.repositories.TradesRepository;
import com.github.trades.validation.TradesSetter;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TradesControllerTest {
    private Trade trade1 =
            new Trade(new ObjectId(), "trade_1", "cpt1", "2019-10-26");

    @Mock
    private TradesRepository repository;

    @InjectMocks
    private TradesController controller;

    @Before
    public void initMocks(){
        controller = new TradesController(new TradesSetter());

        MockitoAnnotations.initMocks(this);

        when(repository.findAll()).thenReturn(List.of(
                trade1,
                new Trade(new ObjectId(), "trade_2", "cpt1", "2019-10-26"),
                new Trade(new ObjectId(), "trade_3", "cpt2", "2019-10-27")));

        when(repository.findByTradeId("trade_1")).thenReturn(
                trade1);
    }

    @Test
    public void testGetAll(){
        assertTrue(controller.getAllTrades().size() == 3);
    }

    @Test
    public void testGetTradeById(){
        assertTrue(controller.getTradeByTradeId("trade_1").equals(trade1));
    }


}