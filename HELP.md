# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/gradle-plugin/reference/html/#build-image)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Lesson
https://www.youtube.com/watch?v=d4HAqS_VfkQ&t=192s

### Test with RSocket Client
* rsc tcp://localhost:8181 --stream --route greetings.Jane
* rsc tcp://localhost:8181 --route greetings-single -d'{"name":"Jane"}'