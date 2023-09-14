package org.example.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;


public class Bot {

    TelegramBot bot = new TelegramBot(System.getenv("BOT_TOKEN"));
    FeaturesController featuresController = new FeaturesController();

    public void serve() {

        bot.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }, e -> {
            if (e.response() != null) {
                e.response().errorCode();
                e.response().description();
            } else {
                e.printStackTrace();
            }
        });
    }

    private void process(Update update) {

        featuresController.update(update);
        featuresController.getRequest().ifPresent(r -> bot.execute(r));
    }
}
