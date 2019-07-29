package club.panda1024.stock.service;

import club.panda1024.stock.model.entity.Stock;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Panda
 * @since 2019-07-29T10:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockServiceTest {

    @Autowired
    private StockService stockService;
    @Autowired
    private StockEaFieldService stockEaFieldService;


    @Test
    public void stockSaveOrUpdateTest() throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {

        List list = stockEaFieldService.listTargetObj(Stock.class);

        for(Object obj : list) {
            Stock s = (Stock) obj;
            if("000508".equals(s.getCode())) {
                stockService.saveOrUpdate(s);
            }
        }
    }


    @Test
    public void stockEntityTest() {
        List<Stock> highStocks = stockService.list(Wrappers.<Stock>lambdaQuery()
                .isNotNull(Stock::getTurnover)
                .gt(Stock::getQuoteChangeRate, 10)
                .orderByAsc(Stock::getTurnover));

        highStocks.forEach(stock -> System.out.println(stock.getCode() + " >> " + stock.mv() + " >> " + stock.cv()));

    }
}
