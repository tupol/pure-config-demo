package example01

import java.time._
import java.time.format.DateTimeFormatter


case class TimeConfig(instant: Instant, duration: Duration,
                      localDateTime: LocalDateTime, zonedDateTime: ZonedDateTime, zone: ZoneId)

object TimeConfig {
  import pureconfig.configurable._
  import pureconfig.ConvertHelpers._
  import pureconfig.generic.auto._
  implicit val localDateConvert = localDateTimeConfigConvert(DateTimeFormatter.ISO_DATE)
  implicit val zonedDateConvert = zonedDateTimeConfigConvert(DateTimeFormatter.ISO_DATE)
}
