package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Data
public class ReviewUrl extends BaseEntity {

    private long id;

    private String url;
    private String title;
    private String author;
    private Date ts;

}
