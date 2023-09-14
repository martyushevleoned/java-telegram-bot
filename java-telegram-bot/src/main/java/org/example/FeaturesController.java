package org.example;

import com.pengrad.telegrambot.model.Audio;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.Voice;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.Optional;

public class FeaturesController {

    private static Message message;


    public void update(Update update) {
        message = update.message();
    }

    public Optional<BaseRequest> getRequest() {

        BaseRequest request = null;

        if (message != null) {
            long chatId = message.chat().id();
            String text = message.text();
            Voice voice = message.voice();
            Audio audio = message.audio();


            if (text != null) {
                request = new SendMessage(chatId, text);

            } else if (voice != null) {
                request = new SendMessage(chatId, voice.toString());

            } else if (audio != null) {
                request = new SendMessage(chatId, audio.toString());
            }
        }

        return Optional.ofNullable(request);
    }
}
