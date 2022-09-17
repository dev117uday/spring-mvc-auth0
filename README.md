# spring-mvc-auth0


## Config

- Create application.yml

```yml
spring:

  security:
    oauth2:
      client:
        registration:
          auth0:
            client-id: 
            client-secret: 
            scope:
              - openid
              - profile
              - email
        provider:
          auth0:
            issuer-uri: https://[domain]/


  datasource:
    url: jdbc:postgresql://localhost:5432/testdb
    username: postgres
    password: password

  jpa:
    hibernate.ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```