
### Api documentation

~~~sh
docker run -p 8081:80 -e SPEC_URL=http://localhost:8080/v3/api-docs redocly/redoc
~~~

### Dependency size with depsize plugin

~~~sh
./gradlew depsize --dependency hibernate-validator
./gradlew depsize --dependency valiktor-core
./gradlew depsize --dependency java-fluent-validator
./gradlew depsize --dependency konform-jvm
./gradlew depsize --dependency yavi

# akkurate
./gradlew depsize --dependency akkurate-core
./gradlew depsize --dependency akkurate-ksp-plugin

./gradlew depsize --dependency thing
./gradlew depsize --dependency kalidation
./gradlew depsize --dependency validoctor
~~~
