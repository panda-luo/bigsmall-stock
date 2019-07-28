package club.panda1024.stock.job;

import club.panda1024.stock.model.entity.StockSimple;
import club.panda1024.stock.service.StockEaFieldService;
import club.panda1024.stock.service.StockSimpleService;
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

    public StockInfoGripperJob(StockEaFieldService stockEaFieldService, StockSimpleService stockSimpleService) {
        this.stockEaFieldService = stockEaFieldService;
        this.stockSimpleService = stockSimpleService;
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



}
