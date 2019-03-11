package com.telegram.currency;


import org.json.JSONObject;

import java.math.BigDecimal;

public class RUB extends AbstractFiatCurrency {


    public RUB() {
        super("https://currate.ru/api/?get=rates&pairs=USDRUB&key=b809b50e59a3bcd1c0fee00407d158d5");
    }

    BigDecimal parseStringToDoublePriceCurrency(String s) {

        JSONObject jsonObject=new JSONObject(s);
        JSONObject jsonObject1= jsonObject.getJSONObject("data");
        return jsonObject1.getBigDecimal("USDRUB");
    }

    @Override
    public String toString() {
        return "RUB";
    }
}
