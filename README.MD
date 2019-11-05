# Spring Boot - Kafka (JSON & Avro) - Zookeeper
Draft of the spring boot service with Kafka messaging support. Contains Examples of implementation
- JSON Serialized messages producer and consumer
- Avro serialized messages producer and consumer

Includes:
- Rest API to test Kafka message sending/receiving
- Swagger UI
- Log4J
- Kafka JsonSerializer and Avro configs for Producer and Consumer
- Actuator and micrometer for Prometheus metrics

### Requirements
1. Docker/Docker Compose
2. maven
3. JDK 1.8
4. Git client


### How to Run?
- Run docker containers fot Mysql and the Application:
```
docker-compose -f ./docker/docker-compose-prod.yml up --build -d
```
Application resources available:
  - Host URL: [http://localhost:9001](http://localhost:9001)
  - Swagger API Docs:  [http://localhost:9001/swagger-ui.html](http://localhost:9001/swagger-ui.html)
 
