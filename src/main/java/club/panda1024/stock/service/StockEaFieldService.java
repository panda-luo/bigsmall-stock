package club.panda1024.stock.service;

import club.panda1024.stock.mapper.StockEaFieldMapper;
import club.panda1024.stock.model.entity.StockEaField;
import club.panda1024.stock.model.entity.StockSimple;
import club.panda1024.stock.model.entity.StockTrend;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class StockEaFieldService extends ServiceImpl<StockEaFieldMapper, StockEaField> {

    private final StockEaParamService stockEaParamService;

    @Autowired
    public StockEaFieldService(StockEaParamService stockEaParamService) {
        this.stockEaParamService = stockEaParamService;
    }

    public List listTargetObj(Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (clazz == null) return null;
        List objResult = Lists.newArrayList();
        List<String> fields = Lists.newArrayList();

        Map<String, String> eaMap = this.list(Wrappers.<StockEaField>lambdaQuery()
                .isNotNull(StockEaField::getName)).stream()
                .collect(toMap(StockEaField::getName, e -> "f" + e.getField()));

        for (Field clzField : clazz.getDeclaredFields()) {
            fields.add(eaMap.get(clzField.getName()));
        }

        List<JSONObject> objs = stockEaParamService.getInfos(fields);

        for (JSONObject obj : objs) {
            Object instance = clazz.getConstructor().newInstance();

            for (String key : eaMap.keySet()) {
                String name = eaMap.get(key);
                if (fields.contains(name)) {
                    String val = (String) obj.get(name);

                    Field field1 = instance.getClass().getDeclaredField(key);
                    field1.setAccessible(true);
                    field1.set(instance, val);
                    field1.setAccessible(false);
                }
            }
            objResult.add(instance);
        }

        return objResult;
    }

    public List<StockTrend> listTrends(List<StockSimple> stocks) {
        List<StockTrend> results = Lists.newArrayList();

        int count = 1;
        for(StockSimple stock : stocks) {
            StockTrend trend = new StockTrend();
            JSONObject data;
            try {
                data = stockEaParamService.getStockDetails(stock.getCode());
            } catch (Exception e) {
                log.error("Get stock:{} failed.", stock.getCode());
                continue;
            }
            trend.setCode(stock.getCode())
                    .setTrend(data.get("trends").toString())
                    .setDate(new Date((Integer) data.get("time") * 1000L));

            log.info("[{}/{}]Success get trend. Stock >> {} - {}.", count, stocks.size(), stock.getCode(), stock.getName());
            count ++;
            results.add(trend);
        }

        return results;
    }

    public static void main(String[] args) {
        String date = DateUtil.formatDate(new Date(1564126786 * 1000L));
        System.out.println(date);

    }

}
