package com.telegram;

import com.telegram.steps.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PhotoBot extends TelegramLongPollingBot {

    //private SingletonMapUser singletonMapUser = SingletonMapUser.getInstance();
    private SingletonPreOrderUserMap singletonPreOrderUserMap = SingletonPreOrderUserMap.getInstance();
    ExecutorService service;



    public void onUpdateReceived(Update update) {
        service.submit(() -> botResponse(update));
           }
    {

        service= Executors.newCachedThreadPool();
    }

    User getUserFromMap(long chatId) {
        User user;
        if (singletonPreOrderUserMap.getUserMap().containsKey(chatId)) {
            user = singletonPreOrderUserMap.getUserFromMap(chatId);
        } else {
            user = new User();
            user.setStep(-1);
        }
        return user;
    }

    private void botResponse(Update update) {

        long chatId = update.getMessage().getChatId();
        List<SendMessage> sendMessageList = null;
        User user = getUserFromMap(chatId);
        //long adminChat = -323839493;
        if (update.getMessage().hasPhoto() && user.getStep() == 4) {
             GetPhotoCheckAndSendToAdmin getPhotoCheckAndSendToAdmin = new GetPhotoCheckAndSendToAdmin();
            try {
                execute(getPhotoCheckAndSendToAdmin.getResponsePhoto(update));
            } catch (TelegramApiException e) {
                     e.printStackTrace();
                     sendMessageList=new ArrayList<>();
                sendMessageList.add(  new SendMessage().setChatId(user.getChatId()).setText("Enter photo check"));
            }
        } else if (update.hasMessage() && update.getMessage().hasText()) {

            if (chatId == -323839493) {
                AdminsChat adminsChat = new AdminsChat();
                sendMessageList = adminsChat.getResponse(update);
            } else if (update.getMessage().getText().equalsIgnoreCase("/start")) {
                StepOneStart stepOneStart = new StepOneStart();
                sendMessageList = stepOneStart.getResponse(update);

            } else if (update.getMessage().getText().equalsIgnoreCase("отмена")) {

                StepCancel stepCancel = new StepCancel();
                sendMessageList = stepCancel.getResponse(update);

            } else if (user.getStep() == 0) {
                StepTwo stepTwo = new StepTwo();
                sendMessageList = stepTwo.getResponse(update);

            } else if (user.getStep() == 1) {
                StepThreeIsSummElectronCurrency stepThreeIsSummElectronCurrency = new StepThreeIsSummElectronCurrency();
                sendMessageList = stepThreeIsSummElectronCurrency.getResponse(update);


            } else if (user.getStep() == 2) {
                StepFourIsSummTrue stepFourIsSummTrue = new StepFourIsSummTrue();
                sendMessageList = stepFourIsSummTrue.getResponse(update);

            } else if (user.getStep() == 3) {
                StepFiveIsAddress stepFiveIsAddress = new StepFiveIsAddress();
                sendMessageList = stepFiveIsAddress.getResponse(update);


            } else if (user.getStep() == -1) {
                UnknownCommand unknownCommand = new UnknownCommand();
                sendMessageList = unknownCommand.getResponse(update);

            }
            executeSendMessage(sendMessageList);

        }

    }


    void executeSendMessage(List<SendMessage> sendMessageList) {
        try {
            for (SendMessage sendMessage : sendMessageList) {
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
        }
    }


    public String getBotUsername() {
        return "vashchestniyviborBot";
    }

    public String getBotToken() {
        return "570451785:AAHLj-GcrN4wzMpMyvGxCarFu1eQeTRxRuQ";
    }


}


