# Attendance tracking app

The application is thought of as an exercise in spring/rest/angular solutions.

## Run

```
mvn clean spring-boot:run
```
simple as that, and the application should be available at localhost:8080

## Remarkable paths

Frontend is served as the root of the application, with three main points (no navigation in place, so you need to know the paths):
```
http://localhost:8080/user
http://localhost:8080/activity
http://localhost:8080/attendance
```

The backend is all configured with '/api' prefix.

## Test calls

in tests.rest file there is a list of example calls you can make to the service. The file can easily be adapted for most http client applications.
