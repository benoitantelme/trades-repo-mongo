package com.github.trades.controller;

import com.github.trades.model.SetterResult;
import com.github.trades.model.Trade;
import com.github.trades.repositories.TradesRepository;
import com.github.trades.validation.TradesSetter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/trades")
public class TradesController {
    @Autowired
    private TradesRepository repository;

    @Autowired
    MongoOperations operations;

    private final TradesSetter setter;

    public TradesController(TradesSetter setter) {
        this.setter = setter;
    }

    @Async
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Future<List<Trade>> getAllTrades() {
        return new AsyncResult<>(repository.findAll());
    }

    @Async
    @RequestMapping(value = "/tradeId={tradeId}", method = RequestMethod.GET)
    public Future<Trade> getTradeByTradeId(@PathVariable("tradeId") String tradeId) {
        return new AsyncResult<>(repository.findByTradeId(tradeId));
    }

    @Async
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyTradeById(@PathVariable("id") ObjectId id, @Valid @RequestBody Trade trades) {
        trades.set_id(id);
        repository.save(trades);
    }

    @Async
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Future<Trade> createTrade(@Valid @RequestBody Trade trades) {
        trades.set_id(ObjectId.get());
        repository.save(trades);
        return new AsyncResult<>(trades);
    }

    @Async
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrade(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

    @Async
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Future<List<Trade>> getTradeByParameters(@RequestParam Map<String, String> params) throws Exception {
        List<Trade> list;

        SetterResult result = setter.setTrade(params);

        if(!result.getExceptions().isEmpty()){
            throw result.getExceptions().get(0);
        }else if(result.isSuccessful()){
            list = repository.findAll(Example.of(result.getTrade(),
                    ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnorePaths("_id", "_class")));
        }else{
            list = new ArrayList<>();
        }

        return new AsyncResult<>(list);
    }

}