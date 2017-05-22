package com.bahri.bot.services
import javax.inject.Inject

import com.bahri.bot.infra.{DBConnection, LineUtils}
import com.bahri.bot.responses._
import com.typesafe.config.ConfigFactory
import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import slick.driver.MySQLDriver.api._
import tables.Tables.{Histories, QuranIndonesia}
import com.bahri.bot.responses.LineBotResponsesFormatters._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/15/17.
  */
class LineBotServiceImpl @Inject()(ws: WSClient) extends LineBotService{

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")
    val lChannelAccessToken = conf.getString("line.channel_access_token")
    val replyUrl = conf.getString("line.replyURL")

    override def replyChat(chats: Seq[Event]): Future[Boolean] = {
        val chat = chats(0)

        val action = for{
            history <-LineBotServiceImpl.historiesTable.filter(_.user===chat.source.userId.getOrElse("")).result.headOption
        }yield history

        DBConnection.db.run(action).map{
            case Some(history) => DBConnection.db.run(LineBotServiceImpl.quranTable.filter(_.id===history.quranId+1).result.headOption).map{
                case Some(ayah) => lineReply(chat.replyToken, s"${ayah.verseid}:${ayah.suraid}. ${ayah.ayaharab.get}, \n artinya: ${ayah.ayahtext}.")
                    updateChat(chat, history.quranId+1)
                case None => DBConnection.db.run(LineBotServiceImpl.quranTable.filter(_.id===1).result.head).map{
                    ayah => lineReply(chat.replyToken, s"${ayah.verseid}:${ayah.suraid}. ${ayah.ayaharab.get}, \n artinya: ${ayah.ayahtext}.")
                        updateChat(chat, 1)
                }

            }

            case None => DBConnection.db.run(LineBotServiceImpl.quranTable.filter(_.id===1).result.head).map{
                ayah => lineReply(chat.replyToken, s"${ayah.verseid}:${ayah.suraid}. ${ayah.ayaharab.get}, \n artinya: ${ayah.ayahtext}.")
                    storingChat(chat, 1)
            }
        }

        Future.apply(true)
    }

    def storingChat(chat: Event, idQuran: Int)= {
        DBConnection.db.run(LineBotServiceImpl.historiesTable.map(mt => (mt.user, mt.quranId)) +=
            (chat.source.userId.get, idQuran))
    }

    def updateChat(chat: Event, idQuran: Int)= {
        DBConnection.db.run(LineBotServiceImpl.historiesTable.filter(_.user===chat.source.userId.get).map(_.quranId).update(idQuran))
    }

    def lineReply(destination: String, msg: String) = {
        val message = PushText("text", s"$msg")
        val replyPayload = ReplyPayload(destination, Seq[PushText](message))
        ws.url(replyUrl).withHeaders("Content-Type" -> "application/json","Authorization" -> s"Bearer $lChannelAccessToken").post(Json.toJson(replyPayload)).map { response =>
            Logger.info(s"response token: $destination, status: ${response.status}, body: ${response.body}")
        }
    }

}

object LineBotServiceImpl{
    val quranTable= TableQuery[QuranIndonesia]
    val historiesTable = TableQuery[Histories]

}

