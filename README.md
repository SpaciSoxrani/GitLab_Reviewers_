GitLab Reviewers
Обработка webhooks из GitLab. Рассылка сообщений в Telegram Bot - уведомление о новых действиях в GitLab.
Для тестирования и работы использую webhook GitLab, Ngrok для отправки вебхуков в локальную сеть.

DataBase

В данной папке реализуется база данных - jpa. Пока что тестово добавляется 2 юзера, через консоль можно добавлять новых. 

Message

В данной папке находиться контроллер сообщений, форма самого сообщения и вебсокет конфигурация.

TelegramBot

Реализация бота. Есть список команд и парсер сообщения, который понимает, содержит ли сообщение команду.

WebHook

Здесь будет парсер json сообщений.
