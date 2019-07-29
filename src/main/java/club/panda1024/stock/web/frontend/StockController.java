package club.panda1024.stock.web.frontend;

import club.panda1024.stock.model.entity.Stock;
import club.panda1024.stock.service.StockService;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panda
 * @since 2019-07-29T14:42
 */
@Slf4j
@RestController
@RequestMapping("stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("{code}")
    public String getStock(@PathVariable String code) {
        Stock stock = stockService.getById(code);

        log.info("Stock: {}", stock);
        return JSONUtil.toJsonStr(stock);
    }
}
