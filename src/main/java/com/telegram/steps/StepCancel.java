package com.telegram.steps;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class StepCancel extends MainStep {

    @Override
    public List<SendMessage> getResponse(Update update)  {
        long chatID=update.getMessage().getChatId();
        singletonPreOrderUserMap.dellUserFromMap(chatID);
        singletonMapUser.dellUserFromMap(chatID);
        SendMessage sendMessage=new SendMessage().setChatId(chatID).setText("Все ваши данные сброшены, начните отсюда /Start");
        return getListMessage(sendMessage);
    }
}
