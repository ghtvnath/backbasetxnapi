# Backbase Transactions API

## Run Tests
To Run the Test cases, in Terminal (command prompt) go to project directory and run the following command

`mvn test`

## Run Application
To run the application in embedded tomcat server, run the following command in in Terminal (command prompt).

`mvn tomcat7:run`

Alternatively, generated war file in the target forlder can be run in a Java application server. 

### API Url for getting all Transactions 
>http://localhost:9090/backbase-txn-api/rest/transactions

### API Url for getting transactions for particular type
>http://localhost:9090/backbase-txn-api/rest/transactions/type/{type}
where type is variable

### API Url for getting total of transactions of type
>http://localhost:9090/backbase-txn-api/rest/transactions/type/{type}/total 
where type is variable 

### Authorization
Above URLs will work with only basic authentication with username - 'user' and password 'password'. (Because this 
is a demo application in-Memory Authentication is used.) Otherwise 401 Unauthorized error will be the response. 
