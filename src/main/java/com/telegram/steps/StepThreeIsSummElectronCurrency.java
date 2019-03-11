package com.telegram.steps;

import com.telegram.Clock;
import com.telegram.User;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class StepThreeIsSummElectronCurrency extends MainStep {
    public StepThreeIsSummElectronCurrency() {
    }

    @Override
    public List<SendMessage> getResponse(Update update) {
        SendMessage sendMessage = new SendMessage();
        User user = singletonPreOrderUserMap.getUserMap().get(update.getMessage().getChatId());
        if (isCountOfBTC(update, user)) {
            createKeyboardifTrue(sendMessage, user);
        } else {
            sendMessage.setText("Некорректная сумма. \nПопробуйте еще раз. \nПример: 5.027");
        }
        sendMessage.setChatId(update.getMessage().getChatId());
        return getListMessage(sendMessage);
    }

    boolean isCountOfBTC(Update update, User user) {
        if (update.getMessage().getText().matches("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)")) {
            user.setSumBTC(new BigDecimal(update.getMessage().getText()));
            user.setSumFiat();
            user.setTimeCheckCourse(new GregorianCalendar());
            user.setStep(2);
            return true;
        }
        return false;
    }

    void createKeyboardifTrue(SendMessage sendMessage, User user) {
        sendMessage.setText("Проверьте правильность введенной вами суммы: \nBTC=" + user.getSumBTC() +"\n" +user.getAbstractFiatCurrencyCurrency().toString() + "=" + user.getSumFiat()+  "\nЕсли все правильно, нажмите «СУММА ВЕРНА». В противном случае нажимайте «СУММА НЕ ВЕРНА» и поменяйте сумму. Оплата принимается в течение часа до \n" + Clock.endOfOrder(user.getTimeCheckCourse(), 60));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":+1: Сумма верна"));
        row2.add(EmojiParser.parseToUnicode(":-1: Сумма не верна"));
        row3.add("Отмена");
        keyboardRows.add(row);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

    }

}
