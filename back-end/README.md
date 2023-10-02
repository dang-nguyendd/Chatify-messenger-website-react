# Getting Started
### Chatify Back End

This is the backend of Chatify 

The deployment version can be access at [http://13.215.156.65:9001/swagger-ui/index.html](http://13.215.156.65:9001/swagger-ui/index.html)

Api docs can be access via [Swagger UI](http://localhost:9001/swagger-ui/index.html#)

To run application locally, you need to have [Docker](https://www.docker.com/) installed.

### Running the application
```bash
$ docker-compose up
```



Alternatively, you can run the application using IntelliJ IDEA with Liberica JDK 17, ElasticSearch, Redis, Kafka, Zookeeper installed.
However you need to change the content of the `application.properties` file to the following:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.kafka.bootstrap-servers=localhost:9092
spring.jpa.properties.hibernate.search.backend.dicover.enabled=false
```

anđ `application.yml` to the following

```yaml
server:
  port: 9001

spring:
  cache:
    type: redis
  autoconfigure:
    exclude: org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration

  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres

  main:
    banner-mode: off
    allow-circular-references: true

  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: chatify-group
      auto-offset-reset: earliest
    producer:
      bootstrap-servers: localhost:9092

  jpa:
    show-sql: true
    properties:
      hibernate:
        search:
          enabled: true
          backend:
            type: elasticsearch
            dynamic_mapping: true
            uris: "http://localhost:9200"

            schema_management:
              minimal_required_status: yellow
          schema_management:
            strategy: drop-and-create

  redis:
    host: localhost
    port: 6379

  sql:
    init:
      mode: always
      platform: postgres

spring-doc:
  swagger-ui:
    path: /api-docs
    enabled: true

```



### Reference Documentation
The following resources is use for reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#web)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#web.security.oauth2.server)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#data.nosql.redis)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

