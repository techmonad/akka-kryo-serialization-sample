# akka-kryo-serialization-sample

Add library dependency to build.sbt:
```scala
libraryDependencies ++= Seq(
  "com.github.romix.akka" %% "akka-kryo-serialization" % "0.5.1"
)

```
2) Update application.conf
```scala
akka {
  actor {
    extensions = ["com.romix.akka.serialization.kryo.KryoSerializationExtension$"]
    serializers {
      kryo = "com.romix.akka.serialization.kryo.KryoSerializer"
    }

    serialization-bindings {
      // classes to serialized
      "com.techmonad.service.WordCountActor$Request" = kryo,
      "com.techmonad.service.WordCountActor$Response" = kryo

    }
  }

}

```
