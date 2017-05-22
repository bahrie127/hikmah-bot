name := """line-bot-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.0",
  "com.typesafe.slick" %% "slick" % "3.1.0",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.0",
  "com.jason-goodwin" %% "authentikat-jwt" % "0.4.1",
  "com.linecorp.bot" % "line-bot-api-client" % "1.6.0",
  "com.linecorp.bot" % "line-bot-model" % "1.6.0",
    "com.squareup.okhttp3" % "okhttp" % "3.6.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true