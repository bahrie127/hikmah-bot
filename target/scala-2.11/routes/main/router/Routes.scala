
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saifulbahri/Documents/workspace/hikmah-bot/conf/routes
// @DATE:Mon May 22 21:25:30 WIB 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:8
  LineBotController_1: com.bahri.bot.controllers.LineBotController,
  // @LINE:16
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:8
    LineBotController_1: com.bahri.bot.controllers.LineBotController,
    // @LINE:16
    Assets_0: controllers.Assets
  ) = this(errorHandler, LineBotController_1, Assets_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, LineBotController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """linebot/callback""", """com.bahri.bot.controllers.LineBotController.callback"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:8
  private[this] lazy val com_bahri_bot_controllers_LineBotController_callback0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("linebot/callback")))
  )
  private[this] lazy val com_bahri_bot_controllers_LineBotController_callback0_invoker = createInvoker(
    LineBotController_1.callback,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "com.bahri.bot.controllers.LineBotController",
      "callback",
      Nil,
      "POST",
      """""",
      this.prefix + """linebot/callback"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/$file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:8
    case com_bahri_bot_controllers_LineBotController_callback0_route(params) =>
      call { 
        com_bahri_bot_controllers_LineBotController_callback0_invoker.call(LineBotController_1.callback)
      }
  
    // @LINE:16
    case controllers_Assets_versioned1_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_0.versioned(path, file))
      }
  }
}