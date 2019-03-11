package com.telegram.currency;

import com.telegram.exceptions.CurrencyException;
import org.json.JSONObject;

import java.math.BigDecimal;


public class BTC extends AbstractElectronCurrency {



    public BTC()  {
        super("https://api.binance.com/api/v3/ticker/bookTicker?symbol=BTCUSDT");

    }


    BigDecimal parseStringToDoublePriceCurrency(String s) {
        JSONObject jsonObject=new JSONObject(s);
        return jsonObject.getBigDecimal("askPrice");
    }
}
