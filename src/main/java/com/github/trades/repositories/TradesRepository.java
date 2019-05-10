package com.github.trades.repositories;

import com.github.trades.Trades;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradesRepository extends MongoRepository<Trades, String> {
    Trades findBy_id(ObjectId _id);
    Trades findByTradeId(String tradeId);
}
