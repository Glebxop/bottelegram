package com.telegram.currency;

import com.telegram.exceptions.CurrencyException;

public abstract class AbstractElectronCurrency extends AbstractCurrency {
    AbstractElectronCurrency(String s) {
        super(s);
    }
}
