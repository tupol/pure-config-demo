import java.net.URL

import com.typesafe.config.{ConfigFactory, ConfigMemorySize}
import pureconfig._
import pureconfig.error.{CannotConvert, ConfigReaderException, ConvertFailure}
import pureconfig.generic.auto._

import scala.concurrent.duration.FiniteDuration
import scala.util.Try

object Config02 extends App {

  val configStr =
    """
      |http {
      |  version = 1.1
      |  server {
      |    host = localhost
      |    port = -8080
      |    timeout = 5s
      |    max-upload = 3
      |    some = [ 1, 2, 3]
      |    map = { "a": 1 , "b": 2}
      |    url : "https://localhost:-8080/blah"
      |    either-str: "w"
      |    either-int: 1
      |  }
      |  opt: ${http.version}
      |}
    """.stripMargin

  implicit val portReader = ConfigReader[Int].emap {
    case n if n >= 0 && n < 65536 => Right(Port(n))
    case n => Left(CannotConvert(n.toString, "Port", "Invalid port number"))
  }
  val config = ConfigFactory.parseString(configStr).resolve()
  println(config.isResolved)
  println(config.root().render())
//  ConfigSource.fromConfig(config)
  ConfigSource.string(configStr)
    .load[AppSettings] match {
    case Left(failures) =>
      println(ConfigReaderException[AppSettings](failures).getMessage())
    case Right(cfg) =>
      cfg.doStuff
  }
}

case class Port(val port: Int) {
  require (port > 0, "Port must be a positive number")
}
case class ServerSettings(url: URL, host: String, port: Port, timeout: FiniteDuration,
                          maxUpload: Long, some: Seq[Int], map: Map[String, Int])
case class HttpSettings(server: ServerSettings, version: Option[Double], opt: Option[Double])
case class AppSettings(http: HttpSettings) {
  def doStuff = {
    println(s"### $http")
    println(http.server.url.getPort)
    println(http.opt)
  }
}
