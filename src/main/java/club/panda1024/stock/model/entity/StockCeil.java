package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockCeil extends BaseEntity {

    private long id;
    private String code;
    private String name;
    private Date firstTs;
    private Date lastTs;
    private Double quoteChangeRate;
    private int count;
    private String countMemo;
    private String tag;
    private String memo;

}

