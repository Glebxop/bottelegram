package com.telegram.currency;


public class SingletonAllCurrency {


    private BTC btc;
    private BYN byn;
    private RUB rub;

    private static SingletonAllCurrency instance;

    {
        btc = new BTC();
        byn = new BYN();
        rub=new RUB();
    }

    public static synchronized SingletonAllCurrency getInstance() {
        if (instance == null) {
            instance = new SingletonAllCurrency();
        }
        return instance;
    }

    private SingletonAllCurrency() {
    }

    public BTC getBtc() {
        return btc;
    }

    public BYN getByn() {
        return byn;
    }

    public void setBtc(BTC btc) {
        this.btc = btc;
    }

    public void setByn(BYN byn) {
        this.byn = byn;
    }

    public RUB getRub() {
        return rub;
    }

    public void setRub(RUB rub) {
        this.rub = rub;
    }
}
