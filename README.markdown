HAML Closures
=============

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


License
-------

Licensed under the Apache License, Version 2.0