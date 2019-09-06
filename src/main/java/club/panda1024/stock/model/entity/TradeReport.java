package club.panda1024.stock.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Data
public class TradeReport {

  private long id;
  private Date tradingDate;
  private String code;


  private float avgPrice;
  private int quantity;



}
