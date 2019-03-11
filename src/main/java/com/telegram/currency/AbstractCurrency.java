package com.telegram.currency;


import com.telegram.Clock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.GregorianCalendar;

public abstract class AbstractCurrency {


    private GregorianCalendar timeLastCheckCourse;
    private String urlCourse;
    private BigDecimal course;


    public AbstractCurrency(String urlCourse) {

        setUrlCourse(urlCourse);
        setCourseCurrency();
    }

    protected void setCourseCurrency() {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(getUrlCourse()).openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            setTimeLastCheckCourse(new GregorianCalendar());
            setCourse(parseStringToDoublePriceCurrency(reader.readLine()));
            System.out.println("Check" + getUrlCourse());
        } catch (IOException e) {
            setCourseCurrency();
        }

    }

    abstract BigDecimal parseStringToDoublePriceCurrency(String readLine);


    public GregorianCalendar getTimeLastCheckCourse() {
        return timeLastCheckCourse;
    }

    public void setTimeLastCheckCourse(GregorianCalendar timeLastCheckCourse) {
        this.timeLastCheckCourse = timeLastCheckCourse;
    }

    public String getUrlCourse() {
        return urlCourse;
    }

    public void setUrlCourse(String urlCourse) {
        this.urlCourse = urlCourse;
    }

    public BigDecimal getCourse() {
        if (course == null) {
            setCourseCurrency();
        } else if (!Clock.countDate(getTimeLastCheckCourse(), 10)) {
            setCourseCurrency();
        }
        return course;
    }

    public void setCourse(BigDecimal course) {
        this.course = course;
    }


}
