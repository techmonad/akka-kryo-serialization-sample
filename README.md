# akka-kryo-serialization-sample

1) Add library dependency to build.sbt:
```scala
libraryDependencies ++= Seq(
  "com.github.romix.akka" %% "akka-kryo-serialization" % "0.5.1"
)

```
2) Update application.conf
```scala
akka {
  extensions = ["com.romix.akka.serialization.kryo.KryoSerializationExtension$"]
  actor {
    serializers {
      kryo = "com.romix.akka.serialization.kryo.KryoSerializer"
    }

    serialization-bindings {
      // classes to be serialized
      "com.techmonad.service.WordCountActor$Request" = kryo,
      "com.techmonad.service.WordCountActor$Response" = kryo

    }
  }

}

```

2) **Testing:** Akka does not serialized messages in a same JVM(sender actor and receiver actor are on same JVM).
 for testing purpose just enable serialize-messages flag.
 
 ```
 akka {
   actor {
     extensions = ["com.romix.akka.serialization.kryo.KryoSerializationExtension$"]
     **//only for testing, don't on for prduction**
     serialize-messages = on
 
 
     serializers {
       kryo = "com.romix.akka.serialization.kryo.KryoSerializer"
     }
 
     serialization-bindings {
       "com.techmonad.service.WordCountActor$Request" = kryo,
       "com.techmonad.service.WordCountActor$Response" = kryo
 
     }
   }
 
 }
```
 
3) **Actor Unit test:**

```scala
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration._

class WordCountActorSpec extends TestKit(ActorSystem("WordCountActorSpec")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  import WordCountActor._

  "WordCountActor " should {
    " count " in {
      val wordCountActor = system.actorOf(Props[WordCountActor], "wordCountActor")
      wordCountActor ! Request("hello hello")
      expectMsg[Response](1.seconds, Response(Map("hello" ->2)))
    }

  }

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }
}

```
