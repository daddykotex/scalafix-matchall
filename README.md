# Scalafix rules for Match All

A linter to help you avoid wildcard pattern matching.

[![Continuous Integration](https://github.com/daddykotex/scalafix-matchall/actions/workflows/ci.yml/badge.svg)](https://github.com/daddykotex/scalafix-matchall/actions/workflows/ci.yml)

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
