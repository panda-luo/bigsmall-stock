package club.panda1024.stock.service;

import club.panda1024.stock.mapper.StockMapper;
import club.panda1024.stock.model.entity.Stock;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Panda
 * @since 2019-07-29T10:44
 */
@Service
public class StockService extends ServiceImpl<StockMapper, Stock> {
}
