package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

public class Bot {

    // Create your bot passing the token received from @BotFather
    TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));

    public void serve() {

        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                // probably network error
                e.printStackTrace();
            }
        });
    }

    private void process(Update update) {
        Message message = update.message();
//        CallbackQuery callbackQuery = update.callbackQuery();
//        InlineQuery inlineQuery = update.inlineQuery();

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
                request = new SendMessage(chatId, audio.duration().toString());
            }
        }

        if (request != null) {
            bot.execute(request);
        }
    }
}
