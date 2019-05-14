package com.github.trades.repositories;

import com.github.trades.model.Trade;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradesRepository extends MongoRepository<Trade, String> {
    Trade findBy_id(ObjectId _id);
    Trade findByTradeId(String tradeId);
}
