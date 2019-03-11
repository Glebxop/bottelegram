package com.telegram.steps;


import com.telegram.PropertyReader;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GetPhotoCheckAndSendToAdmin extends MainStep {


    public SendPhoto getResponsePhoto(Update update) {

        SendPhoto sendPhoto = new SendPhoto();

        List<PhotoSize> photos = update.getMessage().getPhoto();

        String f_id = Objects.requireNonNull(photos.stream().max(Comparator.comparing(PhotoSize::getFileSize))
                .orElse(null)).getFileId();
        PropertyReader propertyReader=new PropertyReader();
        Long adminChat= Long.parseLong(propertyReader.getProperties("Bot.properties").getProperty("adminId"));
        sendPhoto.setPhoto(f_id);
        sendPhoto.setChatId(adminChat);
        sendPhoto.setCaption(update.getMessage().getChatId().toString());
        singletonPreOrderUserMap.dellUserFromMap(update.getMessage().getChatId());
        return sendPhoto;
    }

    @Override
    public List<SendMessage> getResponse(Update update){
        return null;
    }
}
