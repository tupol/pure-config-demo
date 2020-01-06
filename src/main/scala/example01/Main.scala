package example01

import java.time._

import com.typesafe.config.ConfigRenderOptions
import example02.ServiceConfig
import pureconfig.error.ConfigReaderException
import pureconfig.{ConfigReader, ConfigSource, ConfigWriter, error}
import pureconfig.generic.auto._

import scala.util.{Failure, Try}

object Main extends App {

  import TimeConfig._
  val timeConfig = TimeConfig(Instant.now(), Duration.ofHours(1),
    LocalDateTime.now(), ZonedDateTime.now(), ZoneId.systemDefault())

  println(ConfigWriter[TimeConfig].to(timeConfig).render(ConfigRenderOptions.defaults().setComments(false).setOriginComments(false)))

  println(ConfigSource.file("src/main/resources/example01-1.conf")
    .load[TimeConfig].swap.map(error.ConfigReaderException(_).getMessage())
  )

  println(ConfigSource.file("src/main/resources/example01-2.conf")
    .load[TimeConfig].swap.map(error.ConfigReaderException(_).getMessage())
  )
}
