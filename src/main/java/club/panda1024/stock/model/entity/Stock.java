package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(value = "stock")
@ToString
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date listedDate;
    private String industry;
    private String region;
    private String tag;


    public double mv() {
        return this.marketValue / 1_000_000_000; // billion
    }


    public double cv() {
        return this.circulatedValue / 1_000_000_000; // billion
    }

}
