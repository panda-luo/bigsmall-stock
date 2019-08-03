package club.panda1024.stock.service;

import club.panda1024.stock.model.entity.StockTrend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockTrendServiceTest {

    @Autowired
    private StockTrendService stockTrendService;


    @Test
    public void updateStockTrendTest() {
        List<StockTrend> list = stockTrendService.list();

        stockTrendService.saveOrUpdateBatch(list);

    }

}
