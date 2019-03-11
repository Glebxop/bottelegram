package com.telegram.steps;


import com.telegram.User;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.List;

public class StepFourIsSummTrue extends MainStep {


    @Override
    public List<SendMessage> getResponse(Update update) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        if (update.getMessage().getText().equalsIgnoreCase(EmojiParser.parseToUnicode(":+1: Сумма верна"))) {
            summRight(sendMessage, update);
        } else /*if (update.getMessage().getText().equalsIgnoreCase(EmojiParser.parseToUnicode(":-1: Сумма не верна"))) */{
            return summWrong(update);
        }

        return getListMessage(sendMessage);
    }

    private void summRight(SendMessage sendMessage, Update update) {
        User user = singletonPreOrderUserMap.getUserFromMap(update.getMessage().getChatId());
        user.setStep(3);
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText("Введите адрес кошелька, на который необходимо зачислить BTC");
    }

    private List<SendMessage> summWrong( Update update) {
        singletonPreOrderUserMap.getUserMap().get(update.getMessage().getChatId()).setStep(1);
        StepThreeIsSummElectronCurrency stepThreeIsSummElectronCurrency = new StepThreeIsSummElectronCurrency();
        return stepThreeIsSummElectronCurrency.getResponse(update);
    }

}
