Hsoy Templates
==============

Hsoy Templates is a client- and server- side templating system for web and Java.

Based on Google Closure Templates with adding HAML syntax.

Features
--------

  * One template
  * HAML syntax (Haml accelerates and simplifies template creation - see http://haml.info/)
  * Fast
    * compiled into Java (can be used by any JVM based language)
    * compiled into JavaScript
  * Based on Google Closure Templates library

How it works
------------

Converts from

```haml
!!! namespace tests.test2

/
  Greets a person using "Hello" by default.
  @param name The name of the person.
  @param? greetingWord Optional greeting word to use instead of "Hello".
#x1
  %h1
    A Greeting
  {if not $greetingWord}
    .default
      Hello {$name}!
  {else}
    %h2 {$greetingWord} {$name}!
  {/if}
```

to

```
{namespace tests.test2}

/**
* Greets a person using "Hello" by default.
* @param name The name of the person.
* @param? greetingWord Optional greeting word to use instead of "Hello".
*/
{template .x1}
<h1>
  A Greeting
</h1>
{if not $greetingWord}
  <div class='default'>
    Hello {$name}!
  </div>
{else}
  <h2>{$greetingWord} {$name}!</h2>
{/if}
{/template}
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

License
-------

Licensed under the Apache License, Version 2.0