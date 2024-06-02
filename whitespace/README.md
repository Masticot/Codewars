# Regular Expression Parser (Java 17)

This project is a Java 17 implementation of the Codewars exercise titled "Regular Expression Parser," available at [Codewars](https://www.codewars.com/kata/5470c635304c127cad000f0d). This parser is specifically designed to output an abstract syntax tree (AST) representing the structure of regular expressions.

## Description of the Exercise

The task is to implement a simple regular expression parser that can parse and convert regular expressions into their corresponding AST representations. The AST nodes are as follows:

- `new Void()` - Represents an empty RegExp object, returned for incorrect inputs.
- `new Normal(char c)` - Represents a regular character that is not any of the special characters (`()*|.`).
- `new Any()` - Represents any single character (`.`).
- `new ZeroOrMore(RegExp regexp)` - Represents zero or more occurrences of the specified regular expression (`*`).
- `new Or(RegExp r1, RegExp r2)` - Represents a choice between two regular expressions (`|`).
- `new Str(List<RegExp> lstRegexps)` - Represents a sequence of regular expressions.

The parser should respect the usual regular expression rules, including operator precedence, and handle invalid expressions by returning a `new Void()` object.

### Examples

- `"a"` parses to `new Normal('a')`
- `"ab"` parses to `new Str([new Normal('a'), new Normal('b')])`
- `"a.*"` parses to `new Str([new Normal('a'), new ZeroOrMore(new Any())])`
- `"(a.*)|(bb)"` parses to `new Or(new Str([new Normal('a'), new ZeroOrMore(new Any())]), new Str([new Normal('b'), new Normal('b')]))`

Invalid examples such as `""`, `"*"` or `"a**"` should return `new Void()`.

## Features

- **RegExp Class Implementation:** Implements core functionality to parse regular expressions into an AST.
- **Testing Framework:** Includes unit tests that replicate some of the tests found on the Codewars kata, ensuring the parser meets the specified criteria.

### Prerequisites

- Java JDK 17
- Apache Maven (latest version recommended)
