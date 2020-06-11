## Running and testing the application.

### Running the application in local

`mvn spring-boot:run`

### Test the application in local

Access swagger at `http://localhost:8080/swagger-ui.html#`

### Testing the application deployed in AWS

Access swagger at `http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/swagger-ui.html#`

http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/swagger-ui.html

#### Create a new todo

```
curl -X POST "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/todo" \
 -H "accept: */*" -H "Content-Type: application/json" \
 -d "{ \"isCompleted\": true, \"text\": \"string\"}"
```
#### PATCH existing todo

```
curl -X PATCH "http://localhost:8080/todo/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"isCompleted\": false, \"text\": \"string\"}"
```
#### GET existing todo

```
curl "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/todo/1" 
```

#### Todo item does not exist

```
curl "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/todo/100" 
```

#### Validate brackets 

```
curl -X GET "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/tasks/validateBrackets?input=(" 
```
