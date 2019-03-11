package com.telegram.steps;


import com.telegram.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminsChat extends MainStep {

    @Override
    public List<SendMessage> getResponse(Update update) {
        List<SendMessage> sendMessageList = new ArrayList<>();
        String[] strings = update.getMessage().getText().split(",");
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        sendMessageList.add(sendMessage);
        if (update.getMessage().getText().equalsIgnoreCase("show")) {
            showListUserOrder(sendMessage);

        } else if (strings[0].equalsIgnoreCase("send")) {
            //sendHashToUser(sendMessage, strings);
           sendMessageList.add(sendHashToUser(sendMessage, strings,update));
           return sendMessageList;
        }else if (strings[0].equalsIgnoreCase("proc")){
            sendMessage.setText(String.valueOf(User.getPercent()));
        }
        else if (strings[0].equalsIgnoreCase("exproc")){
            exhangeProc(strings[1]);
        }

        return getListMessage(sendMessage);
    }
    private void exhangeProc(String s){
        User.setPercent(Double.parseDouble(s));
    }

    private void showListUserOrder(SendMessage sendMessage) {
        StringBuilder stringBuilder = new StringBuilder();

        if (singletonMapUser.getUserMap().size() > 0) {
            for (Map.Entry<Long, User> entry:singletonMapUser.getUserMap().entrySet()){
               stringBuilder.append(entry.getValue()+"\n-------------");
            }
                 } else {
            stringBuilder.append("пусто,отдыхай");
        }
        sendMessage.setText(stringBuilder.toString());
    }

    private SendMessage sendHashToUser(SendMessage sendMessage, String[] strings,Update update) {

            sendMessage.setChatId(strings[1]);
            sendMessage.setText(strings[2]+"\n Ждем вас снова /Start");
            SendMessage sendMessage1 = new SendMessage();
            try {
            String messageToMe=singletonMapUser.getUserFromMap(Long.valueOf(strings[1])).getSumFiat().multiply(BigDecimal.valueOf(0.015)).toString()+" "+update.getMessage().getChat().getId();
            sendMessage1.setText(messageToMe).setChatId("409175210");
            singletonMapUser.dellUserFromMap(Long.valueOf(strings[1]));
            singletonPreOrderUserMap.dellUserFromMap(Long.valueOf(strings[1]));
            return sendMessage1;
        }catch (Exception e){
            System.out.println("good"+e.getMessage());
            return new SendMessage();}


    }


}
