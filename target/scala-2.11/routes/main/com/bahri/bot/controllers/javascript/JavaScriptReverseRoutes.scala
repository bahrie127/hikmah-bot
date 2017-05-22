
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saifulbahri/Documents/workspace/hikmah-bot/conf/routes
// @DATE:Mon May 22 21:25:30 WIB 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:8
package com.bahri.bot.controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:8
  class ReverseLineBotController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def callback: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "com.bahri.bot.controllers.LineBotController.callback",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "linebot/callback"})
        }
      """
    )
  
  }


}