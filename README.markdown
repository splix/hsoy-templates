Hsoy Templates
==============

Version of Google Closure Templates with HAML syntax.

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

Latest versions: `0.2` for stable version, or `0.3-SNAPSHOT` for development

Maven dependency:
```xml
<dependency>
    <groupId>com.the6hours</groupId>
    <artifactId>hsoy-templates</artifactId>
    <version>0.2</version>
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