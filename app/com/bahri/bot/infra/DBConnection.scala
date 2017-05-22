package com.bahri.bot.infra

/**
  * Created by saifulbahri on 2/17/17.
  */
import slick.driver.MySQLDriver.api._

object DBConnection {
    val db = Database.forConfig("db")
}