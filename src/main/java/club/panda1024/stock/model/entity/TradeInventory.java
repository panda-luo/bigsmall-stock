package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  方案: 采用event+listener监听TradeOffer变化时更新
 *
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TradeInventory extends BaseEntity {

  @TableId
  private long id;

  private long account;

  private String code;
  private int totalQuantity;
  private int freeQuantity;
  private Double avgCost;

}
