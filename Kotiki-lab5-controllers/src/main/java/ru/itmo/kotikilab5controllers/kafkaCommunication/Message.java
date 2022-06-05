package ru.itmo.kotikilab5controllers.kafkaCommunication;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

@Component
public class Message {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    public boolean sendMessage(String topicName, Map<String, String> data) {
    String message = serializeToJson(data);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);
        final boolean[] sendResult = new boolean[1];
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                sendResult[0] = true;
            }

            @Override
            public void onFailure(Throwable ex) {
                sendResult[0] = false;
            }
        });
        return true;
    }

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
