package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Data
public class TradeOffer extends BaseEntity {

  private long id;
  private long tradeId;

  private String code;
  private int type;
  private int quantity;
  private float price;

  private String description;
  private Date ts;

  @TableLogic
  private boolean active;

}
