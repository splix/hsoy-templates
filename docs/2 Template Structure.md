
Hsoy Template Example
---------------------

This template:

```haml
!!! namespace tests

/
  Greets a person using "Hello" by default.

  @param name The name of the person.
  @param? greetingWord Optional greeting word to use instead of "Hello".
#greeting
  %h1
    A Greeting
  {if not $greetingWord}
    .default
      Hello {$name}!
  {else}
    .special
      {$greetingWord} {$name}!
  {/if}
```

will generate for `{name: 'John'}`:

```html
<h1>A Greeting</h2>
<div class="default">
 Hello John!
</div>
```

and for `{name: 'Ivan', greetingWord: 'Privet'}`:

```html
<h1>A Greeting</h2>
<div class="special">
 Privet Ivan!
</div>
```
