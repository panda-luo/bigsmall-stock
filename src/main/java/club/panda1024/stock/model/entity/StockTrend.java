package club.panda1024.stock.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class StockTrend {

    @TableId
    private String code;
    private String trend;
    private Date date;

}
