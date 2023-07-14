# TP microservice


## Start infrastructure (Order is important)
<b>1. </b>`mvn spring-boot:run -f ./eureka-service/pom.xml` </br>
<b>2. </b>`mvn spring-boot:run -f ./config-server/pom.xml` </br>
<b>3. </b>`mvn spring-boot:run -f ./gateway-service/pom.xml` </br>
## Start projects (Order is not important)
<b>1. </b>`mvn spring-boot:run -f ./payment-service/pom.xml` </br>

## Start E2E tests
`E2E test can be performed via : `[e2e-test.http](e2e-test.http)