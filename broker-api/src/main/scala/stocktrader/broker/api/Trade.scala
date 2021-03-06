package stocktrader.broker.api

import play.api.libs.json.{Json, Format}
import stocktrader.TradeType._

case class Trade(symbol: String,
                 shares: Int,
                 tradeType: TradeType,
                 sharePrice: BigDecimal)

object Trade {
  implicit val format: Format[Trade] = Json.format
}
