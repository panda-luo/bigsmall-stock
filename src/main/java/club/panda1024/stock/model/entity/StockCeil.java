package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StockCeil extends BaseEntity {

    private long id;
    private String code;
    private String name;
    private Date ts;
    private int count;
    private String tag;
    private String memo;

}
