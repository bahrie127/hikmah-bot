
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/saifulbahri/Documents/workspace/hikmah-bot/conf/routes
// @DATE:Mon May 22 21:25:30 WIB 2017

package com.bahri.bot.controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final com.bahri.bot.controllers.ReverseLineBotController LineBotController = new com.bahri.bot.controllers.ReverseLineBotController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final com.bahri.bot.controllers.javascript.ReverseLineBotController LineBotController = new com.bahri.bot.controllers.javascript.ReverseLineBotController(RoutesPrefix.byNamePrefix());
  }

}
