package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Trader extends BaseEntity {

  private long id;

  private String name;

  private Double totalValue;
  private Double stockValue;
  private Double quoteChangeRate;
  private Double quoteChange;


}
