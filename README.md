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
 java -jar build/libs/phonebook-0.1-all.jar
```
- Run via gradlew 
```
./gradlew run
```
- Run native image 
```
./build/native/nativeCompile/phonebook 
```
- Run docker container
```
docker run -d  --name <some-name> -p 8080:8080 phonebook
```

### Note

- Native image building requires graalvm to be built and installed
- Interaction with Fauna is possible in two ways: via client and graphql. Default one is via client, to enable graphql mode set property fauna.graphql.enabled: true
- Fauna connection via client is not possible in native image or docker.

## API

- YAML API spec: http://localhost:8080/swagger/phonebook-v1.0.yml
- Swagger API doc: http://localhost:8080/doc
