package club.panda1024.stock.model.dto;

import lombok.Data;

@Data
public class BaseQuery {

    private Integer page;
    private Integer size;
    private Integer total;

}
