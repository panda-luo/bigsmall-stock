package club.panda1024.stock.job;

import club.panda1024.stock.model.entity.StockSimple;
import club.panda1024.stock.model.entity.StockTrend;
import club.panda1024.stock.service.StockEaFieldService;
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
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableScheduling
public class StockInfoGripperJob {

    private final StockEaFieldService stockEaFieldService;

    private final StockSimpleService stockSimpleService;
    private final StockTrendService stockTrendService;

    public StockInfoGripperJob(StockEaFieldService stockEaFieldService, StockSimpleService stockSimpleService, StockTrendService stockTrendService) {
        this.stockEaFieldService = stockEaFieldService;
        this.stockSimpleService = stockSimpleService;
        this.stockTrendService = stockTrendService;
    }


    @Scheduled(cron = "0 30 9 ? * MON-FRI")
    public void getStockSimple() {
        List list = Lists.newArrayList();
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

    @Scheduled(cron = "0 30 9 ? * MON-FRI")
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

}
