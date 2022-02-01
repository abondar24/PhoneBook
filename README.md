# Phone book



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

### Note

- Native image building requires graalvm to be built and installed
