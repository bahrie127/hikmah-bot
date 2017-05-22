package com.bahri.bot.controllers

import javax.inject.Inject

import com.bahri.bot.infra.{AuthAction, LineUtils}
import com.bahri.bot.responses._
import com.typesafe.config.ConfigFactory
import play.api.mvc._
import play.api.libs.json.{JsResult, Json}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.bahri.bot.responses.LineBotResponsesFormatters._
import com.bahri.bot.services.LineBotService
import com.linecorp.bot.client.{LineMessagingServiceBuilder, LineSignatureValidator}
import com.linecorp.bot.model.{PushMessage, ReplyMessage}
import com.linecorp.bot.model.message.TextMessage
import play.api.Logger
import play.api.libs.ws.WSClient

import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/12/17.
  */
class LineBotController @Inject() (ws: WSClient, lineBotService: LineBotService)  extends Controller{

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")
    val lChannelAccessToken = conf.getString("line.channel_access_token")

    def callback = AuthAction.async(parse.json) { request =>
        //Logger.info(s"Log Request all => ${request.body.toString()}")
        LineUtils.handleInvalidJsonFuture {
            request.body.validateOpt[LinePayload] map {
                case pl =>
                    lineBotService.replyChat(pl.get.events).map{
                        case true => Ok(Json.toJson(SuccessBot(200)))
                        case false => UnprocessableEntity(Json.toJson(SuccessBot(422)))
                }
            }
        }
    }

//    def pushMessage = Action.async { request =>
//                    getAuthLine map {auth =>
//                        Logger.info(s"request => ${request.body.toString()}")
//                        val aXLineSignature = request.headers.get("X-Line-Signature").getOrElse("")
//                        Logger.info(s"signature => ${aXLineSignature}")
//                        val valid = new LineSignatureValidator(lChannelSecret.getBytes()).validateSignature(request.body.toString().getBytes, aXLineSignature)
//                        Logger.info(s"valid => ${valid}")
//                        val textMessage = new TextMessage(s"scala Push Test")
//                        //val msg = new TextMessage()
//
//                        val pushMessage = new PushMessage("Ub1ea448d3a8c00dd7e20fe9ae2989bfb",textMessage);
//                        LineMessagingServiceBuilder
//                            .create(lChannelAccessToken)
//                            .build()
//                                .pushMessage(pushMessage)
//                            .execute()
//                        Ok(Json.toJson(SuccessBot(auth)))
//                    }
//
//    }
//
//    def pushJson = Action.async { request =>
//        getAuthLine map {auth =>
//
//            val url ="https://api.line.me/v2/bot/message/push"
//            val text =PushText("text","halo ini dari ws")
//            val userId ="Ub1ea448d3a8c00dd7e20fe9ae2989bfb"
//            //val payload=PushPayload(userId,Seq[PushText](text))
//            val image1 ="https://i2.wp.com/www.jakartahonda.com/wp-content/uploads/2014/11/Honda-New_City.png"
//            val image2 ="https://i1.wp.com/www.hondaphil.com/assets/modelssrc/interior/10-jazz-refresh.jpg?resize=902%2C442"
//            val img =PushImage("image", image1, image2)
//            val payload = PushPayloadImg(userId, Seq[PushImage](img))
//            ws.url(url).withHeaders("Content-Type" -> "application/json","Authorization" -> s"Bearer $lChannelAccessToken").post(Json.toJson(payload)).map { response =>
//                Logger.info(s"response status: ${response.status}, body: ${response.body}")
//            }
//
//            Ok(Json.toJson(SuccessBot(auth)))
//        }
//
//    }

    def getAuthLine: Future[Int]=Future.successful(200)

}
