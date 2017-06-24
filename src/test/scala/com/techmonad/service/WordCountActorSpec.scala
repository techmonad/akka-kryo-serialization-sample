package com.techmonad.service

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
