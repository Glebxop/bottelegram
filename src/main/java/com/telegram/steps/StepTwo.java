package com.telegram.steps;


import com.telegram.User;


import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.List;


public class StepTwo extends MainStep {
    public StepTwo() {
    }

    @Override
    public List<SendMessage> getResponse(Update update) {
        User user = singletonPreOrderUserMap.getUserFromMap(update.getMessage().getChatId());
        user.setAbstractElectronCurrency(singletonAllCurrency.getBtc());

        String currency=update.getMessage().getText();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId()).setText("Введите через точку сумму BTC, которую хотите получить (Пример: 0.001)");
        if (currency.equalsIgnoreCase(EmojiParser.parseToUnicode(":currency_exchange: BYN to BTC"))) {
            bYNtoBTC(user);
        } else if (currency.equalsIgnoreCase(EmojiParser.parseToUnicode(":currency_exchange: RUB to BTC"))) {
            rubtoBTC(user);
        } else {
            sendMessage.setText("Неправильная команда");

        }
        return getListMessage(sendMessage);
    }

    private void bYNtoBTC(User user) {
        user.setAbstractFiatCurrencyCurrency(singletonAllCurrency.getByn());
        user.setStep(1);

    }

    private void rubtoBTC(User user) {
        user.setAbstractFiatCurrencyCurrency(singletonAllCurrency.getRub());
        user.setStep(1);
    }

  /*  private void sendCheck(SendMessage sendMessage, Update update) {
        User user = new User();
        user.setStep(4);
        singletonMapUser.addToUserMap(update.getMessage().getChatId(), user);
        sendMessage.setText("Шли чек");
    }*/
}
