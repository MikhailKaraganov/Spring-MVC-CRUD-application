**Online Store Sber Task**

Проект реализующий CRUD (Create\read\update\delete) функциональность с использованием Spring Boot и Docker Compose для локального запуска. 


Технологии

-Java 17

-Spring Boot

-Docker / Docker Compose

-H2



Шаги для запуска локально с помощью Docker Compose 

1. Клонируйте репозиторий
2. В терминале введите последовательно
   
    mvn clean install
   
    docker-compose build
   
    docker-compose up
   

В результате поднимется контейнер с приложением в вашем локальном Docker

Приложение будет доступно по http://localhost:8080/

Скрины: 
Страница отображения всех сущностей

![image](https://github.com/user-attachments/assets/5300cf29-a76d-4729-ab70-5981c4c3255b)


Страница редактирования

![image](https://github.com/user-attachments/assets/d695d14c-d33b-495a-ad0b-9ecdd9a52d15)

Страница просмотра информации

![image](https://github.com/user-attachments/assets/14bff184-1ae0-474c-8fdd-c0bc2d79d2d7)





