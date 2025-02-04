# Stock Portfolio API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

API to manage stock portfolios by deploying automated trading strategies.

## Stack

- Java 21
- Maven
- Spring Boot
- Spring Web
- Spring Webflux
- Reactor Core
- Spring Validation
- Jakarta Validation
- Spring Data JPA
- MySQL Connector
- Spring Security
- JWT API
- JWT Implementation
- JWT Jackson
- Spring Dotenv
- Lombok
- Spring Test
- H2 Database
- MacOS DNS Resolver
- Docker

## Setup

- Install dependencies:

```
./mvnw clean install
```

- Pull Docker MySQL image for running database server:

```
docker pull mysql:latest
```

- Create an external volume for storing MySQL data:

```
docker volume create stock_portfolio_volume
```

- Run the container:

```
docker compose up -d
```

- Start the application:

```
./mvnw spring-boot:run
```

- Stop the container:

```
docker compose down
```

## Endpoints

Requests can be made to perform the following actions:

- Add Portfolio
- Get Portfolio
- Delete Portfolio

### Add Portfolio

#### Request

```
curl --location 'localhost:8080/api/v2/portfolio/add-portfolio' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Test Portfolio",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "currency": "USD"
}'
```

#### Response

```
{
    "portfolio": {
        "id": 1,
        "createdAt": "2025-01-21 14:48:28",
        "name": "Test Portfolio",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "currency": "USD",
        "amount": 0,
        "stocks": [],
        "transactions": []
    }
}
```

### Get Portfolio

#### Request

```
curl --location --request GET 'localhost:8080/api/v2/portfolio/get-portfolio' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Test Portfolio"
}'
```

#### Response

```
{
    "portfolio": {
        "id": 1,
        "createdAt": "2025-01-21 14:48:28",
        "name": "Test Portfolio",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "currency": "USD",
        "amount": 0.0000,
        "stocks": [],
        "transactions": []
    }
}
```

### Delete Portfolio

#### Request

```
curl --location --request DELETE 'localhost:8080/api/v2/portfolio/delete-portfolio' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Test Portfolio"
}'
```

#### Response

```
{
    "status": "Portfolio deleted successfully"
}
```
