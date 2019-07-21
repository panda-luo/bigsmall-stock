package club.panda1024.stock.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Stock {

    private String code;
    private String name;
    private String market;
    private Date releaseDate;

}
