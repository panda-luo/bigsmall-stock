package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Data
public class StockCeilPromotion extends BaseEntity {

    private Date ts;
    private int ceil1;
    private int ceil2;
    private int ceil3;
    private int ceil4;
    private int ceil5;
    private int ceil6;
    private int ceil7;
    private int ceil8;
    private int ceil9;
    private int ceil10;

    @Setter(AccessLevel.NONE)
    @TableField(exist = false)
    private int total;

}
