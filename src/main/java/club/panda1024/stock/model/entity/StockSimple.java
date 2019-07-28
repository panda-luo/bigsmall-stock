package club.panda1024.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "stock_simple")
public class StockSimple {

    @TableId
    private String code;
    private String name;

}
