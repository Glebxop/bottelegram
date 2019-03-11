package com.telegram.steps;


import com.telegram.PropertyReader;
import com.telegram.User;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.ArrayList;
import java.util.List;

public class StepFiveIsAddress extends MainStep {
    public StepFiveIsAddress() {
    }

    @Override
    public List<SendMessage> getResponse(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String kindOfCurrency = singletonPreOrderUserMap.getUserFromMap(update.getMessage().getChatId()).getAbstractFiatCurrencyCurrency().toString();


        if (isAddresCurrency(update)) {
            if (kindOfCurrency.equalsIgnoreCase("BYN")) {
                sendMessage.setText("Реквизиты на оплату. После оплаты пришлите скрин/фото чека и ожидайте хэш.");
            } else if (kindOfCurrency.equalsIgnoreCase("RUB")) {
                sendMessage.setText("Временно недоступно, используйте другую валюту\n /start");
                singletonPreOrderUserMap.dellUserFromMap(update.getMessage().getChatId());
                return getListMessage(sendMessage);
            }
        } else {
            sendMessage.setText("Неправельный адрес\nПопробуйте снова");
            sendMessage.setChatId(update.getMessage().getChatId());
            return getListMessage(sendMessage);
        }
        PropertyReader propertyReader=new PropertyReader();
        Long adminChat= Long.parseLong(propertyReader.getProperties("Bot.properties").getProperty("adminId"));
        List<SendMessage> sendMessageList = new ArrayList<>();
        sendMessageList.add(sendMessage);
        User user = singletonPreOrderUserMap.getUserFromMap(update.getMessage().getChatId());
        singletonMapUser.addToUserMap(user.getChatId(), user);
        SendMessage sendMessageAdmin = new SendMessage().setChatId(adminChat).setText(user.toString());
        sendMessageList.add(sendMessageAdmin);
        return sendMessageList;
    }

    boolean isAddresCurrency(Update update) {
        if (update.getMessage().getText().matches("[a-zA-Z0-9_]{30,36}")) {
            User user = singletonPreOrderUserMap.getUserMap().get(update.getMessage().getChatId());
            user.setElectronCurrencyAddress(update.getMessage().getText());
            user.setStep(4);
            return true;
        }
        return false;
    }


}
