package ru.itmo.kotikilab5service.KafkaCommunication;

import com.google.gson.Gson;

public class Message {

    public static String serializeToJson(Object object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
    public static <T> T serializeFromJson(String json, Class<T> tClass){
        Gson gson = new Gson();
        T object = gson.fromJson(json, tClass);
        return object;
    }

}
