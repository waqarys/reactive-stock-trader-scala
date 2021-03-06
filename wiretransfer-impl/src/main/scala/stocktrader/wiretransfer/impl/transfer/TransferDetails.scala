package stocktrader.wiretransfer.impl.transfer

import play.api.libs.json._
import stocktrader.wiretransfer.api.Account

case class TransferDetails(source: Account, destination: Account, amount: BigDecimal)

object TransferDetails {
  implicit val format: Format[TransferDetails] = Json.format[TransferDetails]
}
