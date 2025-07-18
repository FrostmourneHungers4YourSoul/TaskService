# Task Service
- REST API для сервиса управления задачами

---
### Сборка и запуск
- Клонируйте проект: git clone git@github.com:FrostmourneHungers4YourSoul/TaskService.git
- Перейдите в директорию проекта: cd [ваша папка с проектом]
- Запустите приложение командой:
```bash
      cd task-service
      mvn clean package
      mvn spring-boot:run
```

---

### Стэк
- Java 17
- Spring Boot 3
- in-memory DB (H2)
- Maven
- Lombok

---

### Функциональность
#### Задачи
    Basic Auth: 
                username: user 
                password: password

- `POST   /tasks          — создание задачи`
- `GET    /tasks          — получение списка всех задач`
- `GET    /tasks/{taskId} — получение задачи по ID`
- `PUT    /tasks/{taskId} — обновление задачи`
- `DELETE /tasks/{taskId} — удаление задачи`
- `GET    /tasks/get      — запрос на адрес: https://api.restful-api.dev/object`

---

### Дополнительные выполненные задания
1) http запрос (GET) на адрес: https://api.restful-api.dev/objects
2) unit test
3) отправка созданной задачи на email
4) Basic Authentication
5) кэширование "Получение списка задач" в Redis
6) [Dockerfile](task-service/Dockerfile)
