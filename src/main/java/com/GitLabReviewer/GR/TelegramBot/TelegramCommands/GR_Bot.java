package com.GitLabReviewer.GR.TelegramBot.TelegramCommands;

import com.GitLabReviewer.GR.DataBase.UserDB.User;
import com.GitLabReviewer.GR.DataBase.UserDB.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class GR_Bot extends TelegramLongPollingBot {
    @Value("${token}")
    private String token;

    @Value("${name}")
    private String username;

    @Value("${myChartId}")
    private String myChartId;

    private final UserRepository userRepository;
    public GR_Bot(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

            if(text.equals("/adduser") || text.equals("Добавить нового пользователя"))
            {
                response.setText("Напишите через запятую информацию о пользователе в следующем формате" +
                        "\nНовый пользователь: ФИО, должность, свободен ли для проверки" +
                        "\nПример:" +
                        "\nНовый пользователь: Смирнова Анастасия, junior, да") ;
                try {
                    execute(response);
                    logger.info("Sent message \"{}\" to {}", text, chatId);
                } catch (TelegramApiException e) {
                    logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
                }

            }
            else if(text.equals("/markup")){
                response.setText("Here is your keyboard");
                // Create ReplyKeyboardMarkup object
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                // Create the keyboard (list of keyboard rows)
                List<KeyboardRow> keyboard = new ArrayList<>();
                // Create a keyboard row
                KeyboardRow row = new KeyboardRow();
                // Set each button, you can also use KeyboardButton objects if you need something else than text
                row.add("Добавить нового пользователя");
                row.add("Установить статус 'Занят' ");
                // Add the first row to the keyboard
                keyboard.add(row);
                // Create another keyboard row
                row = new KeyboardRow();
                // Set each button for the second line
                row.add("Включить уведомления о событиях");
                row.add("Выключить уведомления о событиях");
                // Add the second row to the keyboard
                keyboard.add(row);
                // Set the keyboard to the markup
                keyboardMarkup.setKeyboard(keyboard);
                // Add it to the message
                response.setReplyMarkup(keyboardMarkup);
                try {
                    execute(response);
                    logger.info("Sent message \"{}\" to {}", text, chatId);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else {
                response.setText(command.getCommand().toString());

                try {
                    execute(response);
                    logger.info("Sent message \"{}\" to {}", text, chatId);
                } catch (TelegramApiException e) {
                    logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
                }
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
