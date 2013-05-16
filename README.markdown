Hsoy Templates
==============

Hsoy Templates is a client- and server- side templating system for web and Java.

Based on Google Closure Templates with adding HAML syntax.

Documentation: http://splix.github.io/hsoy-templates/

Features
--------

  * One template
  * HAML syntax (Haml accelerates and simplifies template creation - see http://haml.info/)
  * Fast
    * compiled into Java (can be used by any JVM based language)
    * compiled into JavaScript
  * Based on Google Closure Templates library

Example
-------

```haml
!!! namespace tests

/
  Greets a person using "Hello ...!"
  @param name The name of the person.
#greeting
  %h1
    A Greeting
  %h2
    Hello {$name}!
```

Maven
-----

Latest versions: `0.3` for stable version, or `0.4-SNAPSHOT` for development

Maven dependency:
```xml
<dependency>
    <groupId>com.the6hours</groupId>
    <artifactId>hsoy-templates</artifactId>
    <version>0.3</version>
</dependency>
```

Also you need to add 'The 6 Hours' Maven repository:

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

Snapshot repo is located at `http://maven.the6hours.com/snapshot`

Modules
-------

  * Hsoy Templates Core - https://github.com/splix/hsoy-templates
  * Maven Plugin - https://github.com/splix/maven-hsoy-templates
  * Grails Plugin - https://github.com/splix/grails-hsoy-templates

Author
------
[Igor Artamonov](http://igorartamonov.com), [The 6 Hours](http://the6hours.com)

License
-------

Licensed under the Apache License, Version 2.0