package com.techmonad.service

import akka.actor.Actor
import com.techmonad.akka.Message


class WordCountActor extends Actor {

  import WordCountActor._

  def receive: Receive = {
    case Request(str) =>
      val wordCount = str.split(" +").groupBy(identity).mapValues(_.length)
      sender() ! Response(wordCount)
  }
}


object WordCountActor {

  case class Request(str: String) extends Message

  case class Response(wordCount: Map[String, Int]) extends Message

}