package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import cn.hutool.core.date.DateUtil;
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
  private Date date;





  public long getId() {
    return Long.valueOf(DateUtil.format(date, "yyyyMMdd") + code);
  }
}


