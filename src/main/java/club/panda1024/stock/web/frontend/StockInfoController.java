package club.panda1024.stock.web.frontend;

import club.panda1024.stock.griper.StockInfoGripper;
import club.panda1024.stock.model.base.Wrapper;
import club.panda1024.stock.model.dto.BaseQuery;
import club.panda1024.stock.model.dto.StockInfoDTO;
import club.panda1024.stock.model.entity.StockDetail;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/stock")
public class StockInfoController {

    @Resource
    private StockInfoGripper gripper;


    @GetMapping("basic")
    public Wrapper stockInfo(BaseQuery query) {
        Wrapper wrapper = new Wrapper<>();

        List<StockDetail> details = gripper.basicStockInfoGripper();

        wrapper.setStatus(200);
        wrapper.setData(details);

        log.info("Stock info : {}", details.size());
        return wrapper;
    }

}
