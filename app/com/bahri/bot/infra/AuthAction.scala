package com.bahri.bot.infra

import com.bahri.bot.responses.EmptyDataResponse
import com.linecorp.bot.client.LineSignatureValidator
import com.typesafe.config.ConfigFactory
import play.api.Logger
import play.api.mvc.{ActionBuilder, Request, Result, Results}
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global
import com.bahri.bot.responses.LineBotResponsesFormatters._

import scala.concurrent.Future

/**
  * Created by saifulbahri on 2/18/17.
  */
object AuthAction extends ActionBuilder[Request]{
    val conf = ConfigFactory.load()
    val validToken = conf.getBoolean("enableValidToken")
    override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
        (!validToken || TokenExtractor.extractToken(request)) match {
            case true => LineUtils.handleResponseException(block(request), Seq())
            case false =>
                Future.successful {
                    Results.Unauthorized(Json.toJson(
                        EmptyDataResponse(status = 401, message = "Invalid token")))
                }

        }
    }
}

object TokenExtractor {

    val conf = ConfigFactory.load()
    val lChannelSecret = conf.getString("line.channel_secret")

    def extractToken[A](request: Request[A]): Boolean = {
        request.headers.get("X-Line-Signature") match {
            case Some(token) => new LineSignatureValidator(lChannelSecret.getBytes()).validateSignature(request.body.toString().getBytes, token)
            case None => false
        }
    }

}
