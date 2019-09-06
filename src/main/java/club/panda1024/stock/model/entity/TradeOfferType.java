package club.panda1024.stock.model.entity;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
public enum TradeOfferType {

  BUY(0),
  SELL(1),
  ;

  private int code;

  public int code() {
    return this.code;
  }

  TradeOfferType(int code) {
    this.code = code;
  }
}
