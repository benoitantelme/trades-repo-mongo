package com.github.trades;

import com.github.trades.repositories.TradesRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradesController {
    @Autowired
    private TradesRepository repository;

    @Autowired
    MongoOperations operations;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Trades> getAllTrades() {
        return repository.findAll();
    }


    @RequestMapping(value = "/tradeId={tradeId}", method = RequestMethod.GET)
    public Trades getTradeByTradeId(@PathVariable("tradeId") String tradeId) {
        return repository.findByTradeId(tradeId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyTradeById(@PathVariable("id") ObjectId id, @Valid @RequestBody Trades trades) {
        trades.set_id(id);
        repository.save(trades);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Trades createTrade(@Valid @RequestBody Trades trades) {
        trades.set_id(ObjectId.get());
        repository.save(trades);
        return trades;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrade(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

}