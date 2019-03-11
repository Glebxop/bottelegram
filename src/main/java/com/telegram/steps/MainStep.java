package com.telegram.steps;

import com.telegram.PropertyReader;
import com.telegram.SingletonMapUser;

import com.telegram.SingletonPreOrderUserMap;


import com.telegram.currency.SingletonAllCurrency;
import com.telegram.exceptions.CurrencyException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.ArrayList;
import java.util.List;


public abstract class MainStep {
   protected SingletonMapUser singletonMapUser;
   protected SingletonPreOrderUserMap singletonPreOrderUserMap;
   // static long adminChat ;
    protected SingletonAllCurrency singletonAllCurrency;

    public MainStep() {
        singletonAllCurrency=SingletonAllCurrency.getInstance();
        singletonMapUser=SingletonMapUser.getInstance();
        singletonPreOrderUserMap=SingletonPreOrderUserMap.getInstance();
            }

   /* {
        PropertyReader propertyReader=new PropertyReader();
      adminChat= Long.parseLong(propertyReader.getProperties("Bot.properties").getProperty("adminId"));
   }*/

    abstract public List<SendMessage> getResponse(Update update) throws CurrencyException;
    List<SendMessage> getListMessage(SendMessage sendMessage){
        List<SendMessage> sendMessageList=new ArrayList<>();
        sendMessageList.add(sendMessage);
        return sendMessageList;
    }


}
