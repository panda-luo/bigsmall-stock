package club.panda1024.stock.mapper;

import club.panda1024.stock.model.entity.StockEaField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StockEaFieldMapper extends BaseMapper<StockEaField> {
}
