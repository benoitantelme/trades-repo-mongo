package com.github.trades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Trades {
    @Id
    public ObjectId _id;

    public String tradeId;
    public String counterparty;

    // Constructors
    public Trades() {}

    public Trades(ObjectId _id, String tradeId, String counterparty) {
        this._id = _id;
        this.tradeId = tradeId;
        this.counterparty = counterparty;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String getTradeId() { return tradeId; }
    public void setTradeId(String tradeId) { this.tradeId = tradeId; }

    public String getCounterparty() { return counterparty; }
    public void setCounterparty(String counterparty) { this.counterparty = counterparty; }

}
