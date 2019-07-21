package club.panda1024.stock.tester.gripper;

import club.panda1024.stock.griper.StockInfoGripper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockInfoGripperTester {

    @Resource
    private StockInfoGripper gripper;

    @Test
    public void basicStockInfoGripperTester() {
        gripper.basicStockInfoGripper();
    }



}
