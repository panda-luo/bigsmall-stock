package club.panda1024.stock.griper.helper;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EaHelper {

    public static String EA_BASE_STOCK_URL = "http://77.push2.eastmoney.com/api/qt/clist/get";

    private EaHelper() {
        // can't init
    }

    public static Map<String, Object> defaultGripperGetParam() {
        Map<String, Object> param = Maps.newHashMap();
        param.put("pn", 1);         // page number
        param.put("pz", 5000);      // page size
        param.put("po", 1);         // page
        param.put("np", 1);
        param.put("ut", "bd1d9ddb04089700cf9c27f6f7426281");
        param.put("fltt", 2);
        param.put("invt", 2);
        param.put("fid", "f3");
        param.put("fs", "m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2");

        List<String> fields = Lists.newArrayList();
        fields.add("f12"); // code
        fields.add("f14"); // name
        fields.add("f26"); // releaseDate
        fields.add("f2"); // price
        fields.add("f3"); // 涨跌幅
        fields.add("f102"); // 涨跌幅
        fields.add("f6");
        fields.add("f8");
        fields.add("f9");



        param.put("fields", fields);
        param.put("_", new Date());

        return param;
    }


    public static Map<String, Object> allGripperGetParam() {
        Map<String, Object> param = Maps.newHashMap();
        param.put("pn", 1);         // page number
        param.put("pz", 2);      // page size
        param.put("po", 1);         // page
        param.put("np", 1);
        param.put("ut", "bd1d9ddb04089700cf9c27f6f7426281");
        param.put("fltt", 2);
        param.put("invt", 2);
        param.put("fid", "f3");
        param.put("fs", "m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2");

        List<String> fields = Lists.newArrayList();
        for(int i = 1; i < 200; i++) {
            fields.add(StrUtil.concat(true, "f", String.valueOf(i)));
        }
        param.put("fields", fields);
        param.put("_", new Date());

        return param;
    }
}
