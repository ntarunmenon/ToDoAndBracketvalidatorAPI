## Implementation Details

- I have used an H2 database for persistence.
- Application is deployed using AWS Elastic bean stalk. The configuration of bean stalk is straight forward however I had 
to change the security roles associated with the EC2 instance to allow traffic to reach 8080. 

### How can I make this application better ?. 

Since this is a test I have chosen not to spend too long over engineering. 
However, I would do the following things differently if I were spending more time on this application.

- [Use ECS to deploy the application rather than Elastic bean stalk & make this application CI/CD enabled](https://github.com/ntarunmenon/spring-boot-docker-ecs).   
- I would use `PostGreSql` as a database and then write tests using [TestContainers](https://www.testcontainers.org/)
- [Use cloud formation to automate infrastructure](https://ntarunmenon.gitlab.io/my-blog/2020/04/26/reference-aws-architecture-spring-boot.html)  
- I have used `actuator` to system health status but have not configured it to match the exact API path given in the test  
- Secure the API using `BASIC` authentication and Spring security.

## Running and testing the application.

### Running the application in local

`mvn spring-boot:run`

### Test the application in local

Access swagger at `http://localhost:8080/swagger-ui.html#`

### Testing the application deployed in AWS

Access swagger at http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/swagger-ui.html


#### Create a new todo

```
curl -X POST "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/todo" \
 -H "accept: */*" -H "Content-Type: application/json" \
 -d "{ \"isCompleted\": true, \"text\": \"string\"}"
```
#### PATCH existing todo

```
curl -X PATCH "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/todo/1" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"isCompleted\": false, \"text\": \"string\"}"
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
#### System status

```
curl -X GET "http://todoandbracketvalidatorapi.ap-southeast-2.elasticbeanstalk.com:8080/actuator/status" 
```
