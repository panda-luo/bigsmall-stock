package club.panda1024.stock.model.entity;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("stock_trend")
public class StockTrend {

    @TableId
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private long id;
    private String code;
    private String trend;
    private Date date;

    public long getId() {
        return Long.valueOf(DateUtil.format(date, "yyyyMMdd") + code);
    }
}
