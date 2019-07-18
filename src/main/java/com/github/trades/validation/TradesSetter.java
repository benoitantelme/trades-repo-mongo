
package com.github.trades.validation;

import com.github.trades.model.SetterResult;
import com.github.trades.model.Trade;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TradesSetter {
    private Map<String, Method> fieldToMethodMap;

    public TradesSetter(){
        fieldToMethodMap = Arrays.stream(Trade.class.getDeclaredMethods()).
                filter(method -> method.getName().startsWith("set")).
                collect(Collectors.toMap(
                        method -> getFieldNameFromSetterName(method.getName()),
                        Function.identity()));
    }

    public SetterResult setTrade(Map<String, String> params) {
        Trade trade = new Trade();
        SetterResult result = params.entrySet().stream().
                map(entry -> setField(trade, entry.getKey(), entry.getValue())).
                reduce(new SetterResult(false, new ArrayList<>()),
                        (a, b) -> a.mergeResults(b));
        result.setTrade(trade);

        return result;
    }

    protected SetterResult setField(Trade trade, String fieldName, String value) {
        SetterResult result = new SetterResult();
        if (isFieldValid(fieldName)) {
            try {
                fieldToMethodMap.get(fieldName).invoke(trade, value);
                result.setSuccessful(true);
            }catch(Exception e){
                result.addException(e);
                e.printStackTrace();
            }
        }

        return result;
    }

    public boolean isFieldValid(String fieldName) {
        return fieldToMethodMap.keySet().contains(fieldName);
    }

    /**
     * @param setterName
     * @return method name without set and with a lower case starting letter for the field name
     */
    protected String getFieldNameFromSetterName(String setterName) {
        if (setterName != null && setterName.length() >= 4)
            return setterName.substring(3, 4).toLowerCase() + setterName.substring(4);
        else
            return "";
    }

}