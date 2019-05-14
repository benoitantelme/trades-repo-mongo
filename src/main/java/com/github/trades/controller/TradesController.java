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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trades")
public class TradesController {
    @Autowired
    private TradesRepository repository;

    @Autowired
    MongoOperations operations;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Trade> getAllTrades() {
        return repository.findAll();
    }


    @RequestMapping(value = "/tradeId={tradeId}", method = RequestMethod.GET)
    public Trade getTradeByTradeId(@PathVariable("tradeId") String tradeId) {
        return repository.findByTradeId(tradeId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyTradeById(@PathVariable("id") ObjectId id, @Valid @RequestBody Trade trades) {
        trades.set_id(id);
        repository.save(trades);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Trade createTrade(@Valid @RequestBody Trade trades) {
        trades.set_id(ObjectId.get());
        repository.save(trades);
        return trades;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrade(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public List<Trade> getTradeByParameters(@RequestParam Map<String, String> params) throws Exception {
        List<Trade> list;

        Trade trades = new Trade();
        SetterResult result = params.entrySet().stream().
                map(entry -> TradesSetter.setField(trades, entry.getKey(), entry.getValue())).
                reduce(new SetterResult(false, new ArrayList<>()),
                        (a, b) -> a.mergeResults(b));

        if(!result.getExceptions().isEmpty()){
            throw result.getExceptions().get(0);
        }else if(result.isSuccessful()){
            list = repository.findAll(Example.of(trades,
                    ExampleMatcher.matching()
                            .withIgnoreNullValues()
                            .withIgnorePaths("_id", "_class")));
        }else{
            list = new ArrayList<>();
        }

        return list;
    }

}