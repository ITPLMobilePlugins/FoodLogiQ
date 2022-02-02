## FoodLogiQ
For this project, we created REST APIs for create, read, delete and list commands utilizing events as well as providing an
easy way to build and deploy this within docker container (or docker compose).

## Prerequistics
1.Install Docker Desktop
2.Set JAVA_HOME, we are using Java 11 for this project
3.Set MAVEN_HOME 

## Running The App Using Make File
1. Run the following command to build the package - Make build
2. Run the follwing command to build the docker image - Make image
3. Run the following command to run docker image on container = Make run

## Testing The Application
1.Create event(HttpMethod POST) - http://localhost:8080/food-logiq/event/create

2.Delete event(HttpMethod DELETE) - http://localhost:8080/food-logiq/event/{event_id}

3.Fetch single event(HttpMethod GET) - http://localhost:8080/food-logiq/event/{event_id}

4.Fetch all the event(HttpMethod GET) - http://localhost:8080/food-logiq/events

## H2 Database URL
http://localhost:8080/h2-ui

## Swagger URL


