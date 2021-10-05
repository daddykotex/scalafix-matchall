# Scalafix rules for Match All

A linter to help you avoid wildcard pattern matching.

[![Continuous Integration](https://github.com/daddykotex/scalafix-matchall/actions/workflows/ci.yml/badge.svg)](https://github.com/daddykotex/scalafix-matchall/actions/workflows/ci.yml)

## Details

This rule prevents you from unintentionally adding a case to a pattern match that would match everything. The Scala compiler can do exhaustive checking of pattern match in certain condition. Having a catch all case in the pattern match will make the compiler happy when in reality some cases would be better handled by a specific case.

The rule looks for:

* case where the `_` wild card is used with no guards
* case where the pattern is just a variable that does not start `_` with no guards

You can disable the rule in a given scope using the `@SuppressWarnings("MatchAll")` annotation.

## Usage

Currently, this rule is not published on maven central, as such, the only way to run it is from source. See the [scalafix website](https://scalacenter.github.io/scalafix/docs/developers/tutorial.html#run-the-rule-from-source-code) for more info.

```
scalafix --rules=https://raw.githubusercontent.com/daddykotex/scalafix-matchall/main/rules/src/main/scala/fix/MatchAll.scala
```

## Development

To develop rule:
```
sbt ~tests/test
# edit rules/src/main/scala/fix/MatchAll.scala
```
