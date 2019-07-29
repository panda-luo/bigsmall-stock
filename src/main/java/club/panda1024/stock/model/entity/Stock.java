package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Stock extends BaseEntity {

    @TableId
    private String code;
    private Double current;
    private Double quoteChangeRate;
    private Double quoteChange;
    private Double tradingVolume;
    private Double turnover;
    private Double amplitude;
    private Double turnoverRate;
    private Double pe;
    private Double volumeRadioIndex;
    private Double high;
    private Double low;
    private Double open;
    private Double preClose;
    private Double marketValue;
    private Double circulatedValue;
    private Double pb;
    private Date listedDate;
    private String industry;
    private String region;
    private String tag;

}
