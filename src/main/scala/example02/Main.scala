package example02

import java.net.URL

import com.typesafe.config.ConfigRenderOptions
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter}
import pureconfig.generic.auto._

import scala.concurrent.duration._
import scala.util.{Failure, Try}

object Main extends App {

  val dbConfig = DbConfig("localhost", 123, "my_user", Some("my_pass"),
    Some(Map("option1" -> "value1", "option2" -> "value2")))

  val eservice1 = ExternalServiceConfig(new URL("http://host1:1001"), "1.01", true,
    Duration(10, SECONDS))
  val eservice2 = ExternalServiceConfig(new URL("https://host2:1002"), "2.03", false,
    Duration(10, SECONDS))

  val eservices = Map("CustomerService" -> eservice1, "SupplierService" -> eservice2)


  val serviceConfig = ServiceConfig(dbConfig, eservices)

  println(ConfigWriter[ServiceConfig].to(serviceConfig).render(ConfigRenderOptions.defaults().setComments(false).setOriginComments(false)))

  println(Try(ConfigSource.file("src/main/resources/example01-1.conf")
    .loadOrThrow[ServiceConfig])
      .recover{case t => println(t.getMessage)}
      .get
  )

  println(Try(ConfigSource.file("src/main/resources/example01-2.conf")
    .loadOrThrow[ServiceConfig])
    .recover{case t => println(t.getMessage)}
  )

}
