package club.panda1024.stock.model.eo;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeOfferEO extends BaseRowModel {

  private long id;

  private long tradeId;

  private String code;
  private int type;
  private long quantity;
  private float price;
  private Date date;

  private String description;
  private Date ts;

}

