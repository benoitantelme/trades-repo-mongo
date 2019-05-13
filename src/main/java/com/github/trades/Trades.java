package com.github.trades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.Instant;

public class Trades {
    @Id
    public ObjectId _id;

    public String tradeId;
    public String counterparty;

    /**
     * Manage only the format/parameter YYYY-mm-dd
     */
    public Instant tradeDate;

    // Constructors
    public Trades() {
    }

    public Trades(ObjectId _id, String tradeId, String counterparty, String tradeDate) {
        this._id = _id;
        this.tradeId = tradeId;
        this.counterparty = counterparty;
        this.tradeDate = Instant.parse(tradeDate + "T00:00:00Z");
    }

    // ObjectId needs to be converted to string
    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public Instant getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = Instant.parse(tradeDate + "T00:00:00Z");
    }

}
