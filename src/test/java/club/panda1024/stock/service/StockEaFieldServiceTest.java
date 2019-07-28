package club.panda1024.stock.service;

import club.panda1024.stock.model.entity.StockEaField;
import club.panda1024.stock.model.entity.StockSimple;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockEaFieldServiceTest {

    @Autowired
    private StockEaFieldService stockEaFieldService;

    @Test
    public void listAllTest() {
        List<StockEaField> stockEaFields = stockEaFieldService.list();
        log.info("Stock ea fields : {}.", stockEaFields);
        Assert.assertNotNull(stockEaFields);
    }

    @Test
    public void listStockSimple() throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List list = stockEaFieldService.listTargetObj(StockSimple.class);
        list.forEach(System.out::println);
    }

}
