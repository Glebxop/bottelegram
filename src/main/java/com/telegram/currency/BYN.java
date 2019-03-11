package com.telegram.currency;


import org.json.JSONObject;

import java.math.BigDecimal;

public class BYN extends AbstractFiatCurrency {

    public BYN()  {
       super("http://www.nbrb.by/API/ExRates/Rates/145");
    }

    BigDecimal parseStringToDoublePriceCurrency(String s) {
        JSONObject jsonObject=new JSONObject(s);
        return jsonObject.getBigDecimal("Cur_OfficialRate");
    }

    @Override
    public String toString() {
        return "BYN";
    }
}
