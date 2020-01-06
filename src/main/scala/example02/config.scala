package example02

import java.net.URL

import scala.concurrent.duration.Duration

sealed trait ServiceType
case object CustomerService extends ServiceType
case object SupplierService extends ServiceType

case class ExternalServiceConfig(url: URL, version: String, active: Boolean, timeout: Duration)

case class DbConfig(host: String, port: Int, username: String, password: Option[String],
                    options: Option[Map[String, String]])

case class ServiceConfig(dbConfig: DbConfig, eServices: Map[String, ExternalServiceConfig]) {
  override def toString: String =
    s"""ServiceConfig:
       |- DbConfig:
       |  - $dbConfig
       |- eServices:
       |${eServices.mkString("  - ", "\n  - ", "")}
       |""".stripMargin
}
