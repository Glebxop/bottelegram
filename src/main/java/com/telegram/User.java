package com.telegram;


import com.telegram.currency.AbstractElectronCurrency;
import com.telegram.currency.AbstractFiatCurrency;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.GregorianCalendar;

public class User {

    private String userName;
    private long chatId;
    private AbstractFiatCurrency abstractFiatCurrencyCurrency;
    private AbstractElectronCurrency abstractElectronCurrency;
    private GregorianCalendar timeCheckCourse;
    private int step;
    private BigDecimal sumBTC;
    private BigDecimal sumFiat;
    private String electronCurrencyAddress;
    static private double percent =1.1;



    public static double getPercent() {
        return percent;
    }

    public static void setPercent(double percent) {
        User.percent = percent;
    }

    public String getElectronCurrencyAddress() {
        return electronCurrencyAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setElectronCurrencyAddress(String electronCurrencyAddress) {
        this.electronCurrencyAddress = electronCurrencyAddress;
    }

    public BigDecimal getSumFiat() {
        return sumFiat.setScale(2, RoundingMode.UP);
    }

    public void setSumFiat() {
        this.sumFiat = getSumBTC().multiply(getAbstractElectronCurrency().getCourse().multiply(BigDecimal.valueOf(getPercent())).multiply(getAbstractFiatCurrencyCurrency().getCourse()));
        //this.sumFiat = getSumBTC().multiply(getAbstractElectronCurrency().getCourse().multiply(BigDecimal.valueOf((Clock.isNight(new GregorianCalendar()) ?1.12:1.14)).multiply(getAbstractFiatCurrencyCurrency().getCourse()));
    }

    public BigDecimal getSumBTC() {
        return sumBTC;
    }

    public void setSumBTC(BigDecimal sumBTC) {
        this.sumBTC = sumBTC;

    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public GregorianCalendar getTimeCheckCourse() {
        return timeCheckCourse;
    }

    public void setTimeCheckCourse(GregorianCalendar timeCheckCourse) {
        this.timeCheckCourse = timeCheckCourse;
    }

    public User() {

    }

    public long getChatId() {
        return chatId;

    }

    public AbstractFiatCurrency getAbstractFiatCurrencyCurrency() {
        return abstractFiatCurrencyCurrency;
    }

    public void setAbstractFiatCurrencyCurrency(AbstractFiatCurrency abstractFiatCurrencyCurrency) {
        this.abstractFiatCurrencyCurrency = abstractFiatCurrencyCurrency;
    }

    public AbstractElectronCurrency getAbstractElectronCurrency() {
        return abstractElectronCurrency;
    }

    public void setAbstractElectronCurrency(AbstractElectronCurrency abstractElectronCurrency) {
        this.abstractElectronCurrency = abstractElectronCurrency;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "\nUser " + userName +
                "\nchatId= " + chatId +
                ",\ntime=" + timeCheckCourse.getTime().toString() +
                ",\nBTC=" + sumBTC +
                ",\nFiat=" + getSumFiat() +
                ",\nAddress= " + electronCurrencyAddress;
    }
}
