package com.bahri.bot.services

import com.bahri.bot.responses.Event

import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/15/17.
  */
trait LineBotService {

    def replyChat(chat: Seq[Event]) : Future[Boolean]

}
