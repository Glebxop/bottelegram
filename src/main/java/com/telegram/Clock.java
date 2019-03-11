package com.telegram;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Clock {


    static public boolean countDate(GregorianCalendar previousDate, int minuts) {
        GregorianCalendar previousAdd = new GregorianCalendar();
        previousAdd.setTime(previousDate.getTime());
        previousAdd.add(Calendar.MINUTE, minuts);
        System.out.println(previousDate.getTime() + "  prev");
        System.out.println(new GregorianCalendar().getTime() + "   now");
        return new GregorianCalendar().before(previousAdd);
       /*boolean	after(Object calendar) -
       возвращает значение true, если вызывающий объект класса Calendar содержит более позднюю дату, чем calendar.
boolean	before(Object calendar) -
 возвращает значение true, если вызывающий объект класса Calendar содержит более раннюю дату, чем calendar.*/
    }

    static public Date endOfOrder(GregorianCalendar orderTime, int minuts) {
        orderTime.add(Calendar.MINUTE, minuts);
        return orderTime.getTime();
    }

   static public boolean isNight(GregorianCalendar gregorianCalendar){
        if (gregorianCalendar.getTime().getHours()<9){
            return true;
        }else return false;
    }
}
