package com.GitLabReviewer.GR.TelegramBot.TelegramCommands;

import com.GitLabReviewer.GR.DataBase.WebHookDB.WebHookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class GR_Bot extends TelegramLongPollingBot {
    @Value("${token}")
    private String token;

    @Value("${name}")
    private String username;

    @Value("${myChartId}")
    private String myChartId;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }


    private static final Logger logger = LoggerFactory.getLogger(GR_Bot.class);

        @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(String.valueOf(chatId));
            String text = message.getText();
            Parser newText = new Parser(username);
            ParsedCommand command;
            command = newText.getParsedCommand(text);

            response.setText(command.getCommand().toString()) ;

            try {
                execute(response);
                logger.info("Sent message \"{}\" to {}", text, chatId);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
            }
        }
    }

    public synchronized void sendMsg(String message) {

        SendMessage response = new SendMessage();
        String chatId = myChartId;
        response.setChatId(chatId);
        response.setText(message) ;

        try {
            execute(response);
            logger.info("Sent message \"{}\" to {}", message, chatId);
        } catch (TelegramApiException e) {
            logger.error("Failed to send message \"{}\" to {} due to error: {}", message, chatId, e.getMessage());
        }
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", username, token);
    }

}
