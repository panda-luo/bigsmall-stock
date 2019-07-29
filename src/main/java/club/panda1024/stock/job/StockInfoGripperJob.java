package club.panda1024.stock.job;

import club.panda1024.stock.model.entity.Stock;
import club.panda1024.stock.model.entity.StockSimple;
import club.panda1024.stock.model.entity.StockTrend;
import club.panda1024.stock.service.StockEaFieldService;
import club.panda1024.stock.service.StockService;
import club.panda1024.stock.service.StockSimpleService;
import club.panda1024.stock.service.StockTrendService;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class StockInfoGripperJob {

    private final StockEaFieldService stockEaFieldService;

    private final StockSimpleService stockSimpleService;
    private final StockTrendService stockTrendService;
    private final StockService stockService;

    @Autowired
    public StockInfoGripperJob(StockEaFieldService stockEaFieldService, StockSimpleService stockSimpleService, StockTrendService stockTrendService, StockService stockService) {
        this.stockEaFieldService = stockEaFieldService;
        this.stockSimpleService = stockSimpleService;
        this.stockTrendService = stockTrendService;
        this.stockService = stockService;
    }


    @Scheduled(cron = "0 30 9 ? * MON-FRI")
    public void getStockSimple() {
        List<StockSimple> list = Lists.newArrayList();
        try {
            list = stockEaFieldService.listTargetObj(StockSimple.class);
        } catch (Exception e) {
            log.error("Error: Get stock simple failed.");
        }

        if(CollectionUtil.isEmpty(list)) {
            log.error("Error: Get empty stock simple.");
            return;
        }

        boolean flag = stockSimpleService.saveOrUpdateBatch(list);

        if(flag) {
            log.info("Save or update success.");
        } else {
            log.error("Error: Save stock simple failed.");
        }
    }


    @Scheduled(cron = "0 30 17 ? * MON-FRI")
    public void getStockTrends() {
        List<StockSimple> stockSimples = stockSimpleService.list();
        long s = System.currentTimeMillis();
        List<StockTrend> list = stockEaFieldService.listTrends(stockSimples);
        long e = System.currentTimeMillis();
        log.info("List Trends total costs: {}.", e - s);

        boolean flag = stockTrendService.saveOrUpdateBatch(list);

        if(flag) {
            log.info("Save or update success.");
        } else {
            log.error("Error: Save stock trend failed.");
        }
    }


    @Scheduled(cron = "0 30 17 ? * MON-FRI")
    public void getStock() {
        List<Stock> list = Lists.newArrayList();

        try {
            list = stockEaFieldService.listTargetObj(Stock.class);
        } catch (Exception e) {
            log.error("Error: Get stock simple failed.");
        }

        boolean flag = stockService.saveOrUpdateBatch(list);

        if(flag) {
            log.info("Save or update success.");
        } else {
            log.error("Error: Save stock trend failed.");
        }
    }


}
