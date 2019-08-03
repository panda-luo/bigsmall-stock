package club.panda1024.stock.web.frontend;

import club.panda1024.stock.model.base.WrapMapper;
import club.panda1024.stock.model.base.Wrapper;
import club.panda1024.stock.model.entity.Stock;
import club.panda1024.stock.service.StockService;
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
	public Wrapper get(@PathVariable String code) {
		Stock stock = stockService.getById(code);

		log.info("Stock: {}", stock);
		return WrapMapper.ok(stock);
	}

}
