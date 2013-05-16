General syntax
--------------

  * Hsoy Templates uses braces `{}` for commands.
  * use commands `{lb}` and `{rb}` for left- and right brackets if you need to put them into result html
  * open-close block commands: `{if ...} ... {/if}`
  * use indentation for multi line block commands
  * based on Google Closure Templates, so you can also use docs from here: https://developers.google.com/closure/templates/docs/commands

If you need help for HAML syntax - see http://haml.info/

Print value
-----------

For variable use just a `{$variable}` (notice that `$` is placed inside `{}`)

### General syntax:

```
{<expression>}
{print <expression>}
```

### Print Options

You can post process print

<table>
 <tr>
  <th>Option</th>
  <th>Description</th>
 </tr>
 <tr>
   <td>noAutoescape</td>
   <td>turns off autoescaping</td>
 </tr>
 <tr>
   <td>escapeHtml</td>
   <td>manually HTML-escape the output</td>
 </tr>
 <tr>
   <td>escapeUri</td>
   <td>escape the output so that it can be inserted into a URI parameter</td>
 </tr>
 <tr>
   <td>escapeJsString</td>
   <td>escape the output so that it can be inserted into a JavaScript string</td>
 </tr>
 <tr>
   <td>truncate:<n>[,false]</td>
   <td>truncate a string to a maximum length n with trailing ellipsis, optional ',false' to truncate without an ellipsis.</td>
 </tr>
 <tr>
   <td>changeNewlineToBr</td>
   <td>change newlines (\n, \r, or \r\n) to &lt;br&gt;s.</td>
 </tr>
</table>

### Example

```
{<expression> |noAutoescape}
{print <expression> |noAutoescape}
{<expression> |truncate:10,false}
```

If
--

```
{if <expression>} .... {/if}

{if <expression>}
  .hello
    Hello {$variable}!
{/if}

{if <expression>}
  Hello
{elseif <expression>}
  Bueno
{else}
  %span.error hey?!
{/if}
```

where `<expression>` could be:

  * `{if $variable}`
  * `{if $variable > 5}`
  * `{if $variable == $anotherVariable}`
  * `{if not $variable}`
  * `{if $variable and $anotherVariable}`


Loops
-----

```
{foreach <var> in <array>}
  ...
{ifempty}
  ...
{/foreach}
```

The iterator `var` is a local variable that is defined only in the block. Within
the block, you can also use three built-in functions that only take foreach variables as arguments:

  * `isFirst($var)` returns true only on the first iteration.
  * `isLast($var)` returns true only on the last iteration.
  * `index($var)` returns the current index in the list. List indices are 0-based.