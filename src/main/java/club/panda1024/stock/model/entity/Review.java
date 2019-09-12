package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class Review extends BaseEntity {

    private long id;
    private long reviewerId;
    private Date ts;
    private String url;
    private String title;
    private String content;

    private String attachment;

    private List<String> neg;
    private List<String> positive;

    public long getId() {
        String pre = DateUtil.format(ts, "yyyyMMdd");
        String suf;
        if(reviewerId < 10) {
            suf = "00" + reviewerId;
        } else if (reviewerId < 100) {
            suf = "0" + reviewerId;
        } else {
            suf = "" + reviewerId;
        }
        return Long.valueOf(pre + suf);
    }
}
