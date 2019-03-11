package com.telegram.steps;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class UnknownCommand extends MainStep {
    @Override
    public List<SendMessage> getResponse(Update update) {
        SendMessage sendMessage=new SendMessage().setText("Неверная команда. Начните отсюда.\n/Start").setChatId(update.getMessage().getChatId());
        return getListMessage(sendMessage);
    }
}
