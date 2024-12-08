# Указываем базовый образ с JDK
FROM openjdk:17-jdk-slim

# Устанавливаем переменную окружения для хранения рабочего каталога
WORKDIR /app

# Копируем собранный .jar файл приложения в контейнер
COPY target/onlineStoreSberTask-0.0.1-SNAPSHOT.jar /app/onlineStoreSberTask.jar

# Открываем порт для приложения (по умолчанию Spring Boot работает на 8080)
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "onlineStoreSberTask.jar"]
