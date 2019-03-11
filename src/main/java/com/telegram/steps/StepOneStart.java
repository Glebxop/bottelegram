package com.telegram.steps;

import com.telegram.User;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StepOneStart extends MainStep {


    @Override
    public List<SendMessage> getResponse(Update update) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId()).setText(EmojiParser.parseToUnicode(":game_die: Выберите валюту обмена"));
        showKeybord(sendMessage);
        singletonPreOrderUserMap.addToUserMap(update.getMessage().getChatId(),createUser(update));
        return getListMessage(sendMessage);
    }

    private void showKeybord(SendMessage sendMessage) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton keyboardButton=new KeyboardButton();
        keyboardButton.setText(EmojiParser.parseToUnicode(":currency_exchange: BYN to BTC"));
        row.add(keyboardButton);
        row2.add(EmojiParser.parseToUnicode(":currency_exchange: RUB to BTC"));
        row3.add("Отмена");
        keyboardRows.add(row);
        keyboardRows.add(row2);
        keyboardRows.add(row3);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

    }

    User createUser(Update update) {
        User user = new User();
        user.setChatId(update.getMessage().getChatId());
        user.setStep(0);
        user.setUserName(update.getMessage().getChat().getFirstName());
        return user;
    }
}
