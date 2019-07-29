package club.panda1024.stock.mapper;

import club.panda1024.stock.model.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Panda
 * @since 2019-07-29T10:43
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
