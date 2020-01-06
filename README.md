# The Pure Joy of PureConfig

## Introduction

[PureConfig] is a configuration utility library written in Scala that puts together the TypeSafe/[Lightbend Config] and [Shapeless] to provide a clean way to load and validate the configuration.

The following are the main features of the [PureConfig] library:
- support for most basic types as well as support for type from various other [libraries](https://pureconfig.github.io/docs/library-integrations.html) (like akka, joda, squants…)
- data type extraction and validation
- configuration errors aggregation
- configuration composition


## Usage

To get [PureConfig] into your project is as easy as adding one line dependency to the `build.sbt`:
```scala
libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.12.2"
```

When experimenting with various types, one might hit compilation problems and the following addition to `build.sbt` will save a lot of time:

```scala
scalacOptions += "-Xmacro-settings:materialize-derivations"
````




[PureConfig]: https://github.com/pureconfig/pureconfig/
[Lightbend Config]: https://github.com/lightbend/config
[Shapeless]: https://github.com/underscoreio/shapeless-guide
