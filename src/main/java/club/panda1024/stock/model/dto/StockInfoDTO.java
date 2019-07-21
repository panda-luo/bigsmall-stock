package club.panda1024.stock.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StockInfoDTO {

    private String code;
    private String name;
    private String market;
    private String city;

}
