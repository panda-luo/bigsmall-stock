package club.panda1024.stock.model.entity;

import club.panda1024.stock.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class Reviewer extends BaseEntity {

    private long id;
    private String name;
    private String url;
    private int source;

    private long follows;

    public enum Source {
        TAO_GU_BA,
        ;
    }

}
