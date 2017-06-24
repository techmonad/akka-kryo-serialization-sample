  name := "akka-kryo-serialization"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.3",
  "com.github.romix.akka" %% "akka-kryo-serialization" % "0.5.1",
  "org.scalatest" %% "scalatest" % "3.0.3" % Test,
  "com.typesafe.akka" %% "akka-testkit" % "2.5.3" % Test

)
        