# Phone book

Yet another dummy phone book application based on Micronaut framework and graphql

## Build and run

### Build

- Build regular jar
```
./gradlew clean build
```

- Build graalvm native image
```
./gradlew nativeCompile
```

- Build docker image with graalvm

```
./gradlew dockerBuildNative
```

### Run

- Run regular jar

```
 java -jar build/libs/phonebook-0.1-all.jar -Dmicronaut.environments=<env>
```

Possible environments to run: dev, native.

- Run via gradlew

```
./gradlew run
```

- Run native image

```
./build/native/nativeCompile/phonebook -Dmicronaut.environments=native
```

- Run docker container
```
docker run -d  --name <some-name> -p 8080:8080 -e FAUNA_KEY=$FAUNA_KEY phonebook
```

## API

- YAML API spec: http://localhost:8080/swagger/phonebook-v1.0.yml
- Swagger API doc: http://localhost:8080/doc

## Heroku access

- app deployed on heroku can be accessed via https://phone-book-fauna.herokuapp.com/
- app deployed on heroku in docker container can be accessed via https://phone-book-docker.herokuapp.com/

### Note

- Native image building requires graalvm to be built and installed
- Interaction with Fauna is possible in two ways: via client and graphql. Default one is via client, to use graphql run
  with -Dmicronaut.environments=native
- Fauna connection via client is not possible in native image or docker.
- For local usage set fauna API Key in environment variable FAUNA_KEY
- Docker deployment on heroku requires adding fauna key manually
