package club.panda1024.stock.service;

import club.panda1024.stock.job.StockInfoGripperJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockInfoGripperJobTest {

    @Autowired
    private StockInfoGripperJob stockInfoGripperJob;

    @Test
    public void stockSimpleJobTest() {
        stockInfoGripperJob.getStockSimple();
    }

    @Test
    public void getStockTrendsTest() {
        stockInfoGripperJob.getStockTrends();
    }

    @Test
    public void getStockTest() {
        stockInfoGripperJob.getStock();
    }

}
