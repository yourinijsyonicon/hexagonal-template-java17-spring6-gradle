# Hexagonal Template

Can be used as a starting point for ports and adapters or hexaginal architecture based applications.

It is free to use.
 
## Build
run `gradle build`

or use gradle wrapper `./gradlew build` (or use the gradlew.bat file for Windows OS)

## Run locally
Run TemplateApplication with local profile

## Test locally
Swagger UI available at :
http://localhost:8080/swagger-ui/index.html

## Links
...

## Remarks
#### Properties Constructor binding is not working:
* https://docs.spring.io/spring-boot/docs/3.0.3-SNAPSHOT/reference/htmlsingle/#native-image.advanced.nested-configuration-properties (see note)
  * _Please use public getters and setters in all cases, otherwise the properties will not be bindable._
* https://github.com/spring-projects/spring-boot/issues/33409
* https://github.com/spring-projects/spring-boot/issues/32765
