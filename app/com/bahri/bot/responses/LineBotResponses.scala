package com.bahri.bot.responses

import play.api.libs.json.Json

import scala.concurrent.Future
/**
  * Created by saifulbahri on 2/12/17.
  */


    /**
      * {
   "events":[
      {
         "type":"message",
         "replyToken":"051b994303b848b6ba6a07ca27a0cb85",
         "source":{
            "userId":"Ub1ea448d3a8c00dd7e20fe9ae2989bfb",
            "type":"user"
         },
         "timestamp":1486840162021,
         "message":{
            "type":"text",
            "id":"5634162383406",
            "text":"haloo"
         }
      }
   ]
}
      */

case class Message(`type`:String, id:String, text: Option[String], stickerId: Option[String], packageId: Option[String],
                   title: Option[String], address: Option[String], latitude: Option[BigDecimal], longitude: Option[BigDecimal])
case class Source(userId: Option[String], roomId: Option[String], groupId: Option[String], `type`: String)
case class Event(`type`: String, replyToken: String, source: Source, timestamp: Long, message: Message)
case class LinePayload(events: Seq[Event])
case class SuccessBot(status: Int =200)
case class EmptyDataResponse(status: Int, message: String, data: Map[String, String] = Map.empty[String, String])

//text
case class PushText(`type`: String, text: String)
//image
case class PushImage(`type`: String, originalContentUrl: String, previewImageUrl: String)
case class PushPayload(to: String, messages: Seq[PushText])
case class ReplyPayload(replyToken: String, messages: Seq[PushText])
case class PushPayloadImg(to: String, messages: Seq[PushImage])

/**
  * {
  "type": "template",
  "altText": "this is a carousel template",
  "template": {
      "type": "carousel",
      "columns": [
          {
            "thumbnailImageUrl": "https://example.com/bot/images/item1.jpg",
            "title": "this is menu",
            "text": "description",
            "actions": [
                {
                    "type": "postback",
                    "label": "Buy",
                    "data": "action=buy&itemid=111"
                },
                {
                    "type": "postback",
                    "label": "Add to cart",
                    "data": "action=add&itemid=111"
                },
                {
                    "type": "uri",
                    "label": "View detail",
                    "uri": "http://example.com/page/111"
                }
            ]
          }
      ]
  }
}
  */

case class Action(`type`: String, label: String, data: String)
case class Column(thumbnailImageUrl: String, title: String, text: String, actions: Seq[Action])
case class Carousel(`type`: String, columns: Seq[Column])
case class TempleteProduct(`type`: String, altText: String, template:Carousel)

case class ReplyPayloadTemplate(replyToken: String, messages: Seq[TempleteProduct])


object LineBotResponsesFormatters{
   implicit val emptyDataResponseFormatter = Json.format[EmptyDataResponse]
   implicit val messageFormatter =Json.format[Message]
   implicit val sourceFormatter = Json.format[Source]
   implicit val eventFormatter = Json.format[Event]
   implicit val linePayloadFormatter = Json.format[LinePayload]
   implicit val successFormatter = Json.format[SuccessBot]
   implicit val pushTextFormatter = Json.format[PushText]
   implicit val pushImageFormatter = Json.format[PushImage]
   implicit val pushPayloadFormatter = Json.format[PushPayload]
   implicit val pushPayloadImgFormatter = Json.format[PushPayloadImg]
   implicit val replyPayloadFormatter = Json.format[ReplyPayload]
   implicit val actionFormatter =  Json.format[Action]
   implicit val columnFormatter =  Json.format[Column]
   implicit val CarouselFormatter =  Json.format[Carousel]
   implicit val templeteFormatter =  Json.format[TempleteProduct]
   implicit val replyTempleteFormatter =  Json.format[ReplyPayloadTemplate]
}



