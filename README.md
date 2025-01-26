# Spring Boot Application

## Description
This Spring Boot application is designed for resource creation and management. It includes the following features:

- **Resource Management**: Create, edit, and maintain resources with revision history.
- **Email Notifications**: Send emails for various events using MailHog as the SMTP server.
- **Activity Logging**: Keep track of all resource-related activities.

The application is containerized with Docker and uses PostgreSQL as the database.

---

## Prerequisites


### 1. Clean and Build the Project

Use Maven to clean and build the project:

```bash
mvn clean install
```

### 2. Docker Setup

Navigate to the `devops` folder:


Run the Docker Compose setup:


```bash
docker-compose up
```

This setup includes the following services:

- **PostgreSQL**: Database service.
- **MailHog**: Mock SMTP server accessible at [http://localhost:8025](http://localhost:8025).



The application will start and be accessible at [http://localhost:8080](http://localhost:8080).

---

### 3. MailHog

MailHog is used as both the SMTP server and mock inbox. To view sent emails:

1. Open [http://localhost:8025](http://localhost:8025) in your browser.
2. Check the inbox for sent emails.


## Author
[andreloucas](https://github.com/andreloucas/assignment-frontend)