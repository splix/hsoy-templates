  * [Hsoy Templates Core](https://github.com/splix/hsoy-templates) - low-level library
  * [Maven Plugin](https://github.com/splix/maven-hsoy-templates)
  * [Grails Plugin](https://github.com/splix/grails-hsoy-templates)

Maven
-----

```xml
<dependency>
    <groupId>com.the6hours</groupId>
    <artifactId>hsoy-templates</artifactId>
    <version>0.4</version>
</dependency>
```

+ you need extra repository:

```xml
<repositories>
    <repository>
        <id>the6hours-release</id>
        <url>http://maven.the6hours.com/release</url>
        <releases><enabled>true</enabled></releases>
        <snapshots><enabled>false</enabled></snapshots>
    </repository>
</repositories>
```