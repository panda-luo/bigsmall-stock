package club.panda1024.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stock_ea_field")
public class StockEaField {

    @TableId
    private int field;
    private String name;
    private String memo;

}
