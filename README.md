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
- Spring Data JPA
- MySQL Connector
- Docker
- Spring Dotenv
- Lombok
- Spring Test
- H2 Database
- MacOS DNS Resolver

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

Requests can be made to get the following resources:

- Portfolio
    - Created At
    - Name
    - Desc
    - Currency
    - Total value
    - Number of companies
    - Number of stocks
    - Global Quote
- Stock Time Series Intraday
    - OHLCV
    - Adjusted
    - Including extended hours
    - Intervals: 1min, 5min, 15min, 30min, 60min
- Stock Time Series Daily
    - OHLCV
    - Not adjusted
- Stock Time Series Weekly
    - OHLCV
    - Adjusted
- Stock Time Series Monthly
    - OHLCV
    - Adjusted

Instances can be created, fetched, or deleted for the default model class whose data is stored in a Docker MySQL volume (mysql_volume).

### Requests

- GET /:
```
curl -i -X GET http://localhost:8080/api/v1/model/
```

- POST /new-model: 
```
curl -i -X POST "http://localhost:8080/api/v1/model/new-model?name=ABC"
```

- GET /get-model:
```
curl -i -X GET "http://localhost:8080/api/v1/model/get-model?name=ABC"
```

- DELETE /delete-model:
```
curl -i -X DELETE "http://localhost:8080/api/v1/model/delete-model?name=ABC"
```
