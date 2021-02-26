# VoteService application
This Rest API provides a simple vote management back-end to simulate assemblies voting by each member of a coperative.
You can create a vote topic, session for each vote topic and members voting through a valid and able CPF documentation.

## Running in Docker container
### You need to install:
```
- docker
- docker-compose (if it is not installed in your operating system)
```

### To execute the application : 
```
- docker-compose down (if containers were previously uploaded)
- docker-compose build --no-cache
- docker-compose up -d
```

#### Example of usage
Suppose that your application is in docker and you want to access.
```
- Enter http://localhost:8081/swagger-ui.html and use Swagger-ui to test application`
```

## Running in Cloud
If you want to execute in cloud you need to:
```
- create an account in cloud platform of your preference. For example, Heroku.
- upload the voteservice application.
```

### Heroku Cloud
```
- create an account in heroku
- Click in New -> New app
- In the app name identify your application with an available name (like voteservice)
- Click in create app
- Deploy vote-service repository
- Make sure that Postgres and RabbitMQ are installed in the Heroku and run
```

#### Example of usage
Suppose that your application is on Heroku, called "voteserviceapp" and you want to access.
```
- Enter https://voteserviceapp.herokuapp.com/swagger-ui.html and use Swagger-ui to test application
```

## Performance test
To do the performance test the Jmeter was used. Open the file 

## API versioning
Changes and new features are implemented in new versions instead of altering the same version. With versioning old clients can continue to use the oldest API version and upgrading their applications to the newer one.
There are two common versinoing methods used:

### Versioning in the API URLs
One common way to implement API versioning, and used in this project, is to embed the version number in the API URLs.
For example, https://voteserviceapp.herokuapp.com/api/v1/votetopics stands for the /votetopics endpoint of API version 1.

The project structure below can be applied to organize API versioning. For this project this proposal was not applied due it is a simple project example. Only API URLs versioning was applied.

```
api/
    common/
        controllers/
            ...
        models/
            ...
    modules/
        v1/
            controllers/
                VoteTopicController.java
				...
            models/
                VoteTopic.java
				...
            VoteService.java
        v2/
            controllers/
                VoteTopicController.java
				...
            models/
                VoteTopic.java
				...
            VoteService.java
```
			
### Versioning in the HTTP request headers
Another method of API versioning, which has gained momentum recently, is to put the version number in the HTTP request headers.

For example:
```
// via a parameter
Accept: application/json; version=v1
// via a vendor content type
Accept: application/vnd.company.myapp-v1+json
```
(source: https://www.yiiframework.com/doc/guide/2.0/en/rest-versioning)





