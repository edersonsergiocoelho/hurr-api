package br.com.escconsulting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class TelegramBot extends TelegramLongPollingBot {


    public TelegramBot(@Value("${telegram.bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public String getBotUsername() {
        // Replace with your bot's username
        return "your_bot_username";
    }

    @Override
    public String getBotToken() {
        // Replace with your bot's token
        return "16013fb4dcb4fdffc031946d9b7c85c8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Handle incoming updates (optional)
    }

    public void sendCode(String chatId, String code) {
        try {

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Seu código de verificação é: " + code);

            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}