package com.telegram;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SingletonMapUser {

    private Map<Long, User> userMap;
    private static SingletonMapUser instance;

    {userMap=new HashMap<>();}

    public static synchronized SingletonMapUser getInstance() {
        if (instance == null) {
            instance = new SingletonMapUser();
        }
        return instance;
    }

    private SingletonMapUser() {
    }

    public Map<Long, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Long, User> userMap) {
        this.userMap = userMap;
    }

    public void addToUserMap(Long chatId, User user) {
        getUserMap().put(chatId, user);
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(90);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getUserMap().remove(chatId);
        });
        thread.start();
    }

    public void dellUserFromMap(Long chatId) {
        getUserMap().remove(chatId);
    }
    public User getUserFromMap(Long chatId){
        return getUserMap().get(chatId);
    }
}
