package com.techmonad.service

import akka.actor.Actor


class WordCountActor extends Actor {

  import WordCountActor._

  def receive: Receive = {
    case Request(str) =>
      val wordCount = str.split(" +").groupBy(identity).mapValues(_.length)
      sender() ! Response(wordCount)
  }
}


object WordCountActor {

  case class Request(str: String)

  case class Response(wordCount: Map[String, Int])

}