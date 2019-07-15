package com.github.trades.model;

import java.util.ArrayList;
import java.util.List;

public class SetterResult {
    private boolean successful;
    private List<Exception> exceptions = new ArrayList<>();
    private Trade trade;

    public SetterResult(){}

    public SetterResult(boolean successful, List<Exception> exceptions) {
        this.successful = successful;
        this.exceptions = exceptions;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Exception> exceptions) {
        this.exceptions = exceptions;
    }

    public void addException(Exception exception) {
        this.exceptions.add(exception);
    }

    public void setTrade(Trade trade) { this.trade = trade; }
    public Trade getTrade() { return trade; }

    public SetterResult mergeResults(SetterResult result){
        successful = successful || result.isSuccessful();
        this.exceptions.addAll(result.exceptions);

        return this;
    }

}