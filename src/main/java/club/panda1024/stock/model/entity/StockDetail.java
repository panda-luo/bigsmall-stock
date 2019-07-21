package club.panda1024.stock.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StockDetail {

    private String code;
    private String name;
    private String releaseDate;
    private String price;
    private String zhangdiefu;
    private String city;
    private String chengjiaoe;
    private String huanshoulv;
    private String pe;



}
