package com.bahri.bot.infra

import com.bahri.bot.services.{LineBotService, LineBotServiceImpl}
import com.google.inject.AbstractModule

/**
  * Created by saifulbahri on 2/17/17.
  */
class BotDIModule extends AbstractModule{
    override def configure(): Unit = {
        bind(classOf[LineBotService]).to(classOf[LineBotServiceImpl])
    }
}
