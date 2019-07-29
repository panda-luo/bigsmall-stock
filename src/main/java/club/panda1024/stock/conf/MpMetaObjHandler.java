package club.panda1024.stock.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Panda
 * @since 2019-07-29T13:11
 */
@Slf4j
@Component
public class MpMetaObjHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("Start insert fill..");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("creator", "Panda", metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updater", "Panda", metaObject);
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("Start update fill..");
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updater", "Panda", metaObject);
    }
}
