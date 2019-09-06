package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Panda
 * @since 2019-07-30T13:40
 */
@Data
@Accessors(chain = true)
@TableName("stock_trend_simple")
@EqualsAndHashCode(callSuper = true)
public class StockTrendSimple extends BaseEntity {

  @TableId
  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private long id;

  private String code;
  private String name;
  private Date date;

  private Double current;

  @Setter(AccessLevel.NONE)
  @TableField(exist = false)
  private Double quoteChangeRate;
  private Double quoteChange;

  private Double tradingVolume;
  private Double turnover;

  private Double high;
  private Double low;
  private Double open;
  private Double preClose;

  private Double marketValue;
  private Double circulatedValue;

  @Setter(AccessLevel.NONE)
  @TableField(exist = false)
  private Double pe;

  @Setter(AccessLevel.NONE)
  @TableField(exist = false)
  private Double pb;


  public Double getQuoteChangeRate() {
    return NumberUtil.div(quoteChange.doubleValue(), current - quoteChange);
  }

  // TODO - panda formula
  public Double getPe() {
    return null;
  }
  // TODO - panda formula
  public Double getPb() {
    return null;
  }

  public long getId() {
    return Long.valueOf(DateUtil.format(date, "yyyyMMdd") + code);
  }
}


