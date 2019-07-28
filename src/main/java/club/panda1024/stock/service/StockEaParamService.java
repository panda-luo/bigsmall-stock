package club.panda1024.stock.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class StockEaParamService {

    private final static String STOCK_BS_DAILY_URL = "http://77.push2.eastmoney.com/api/qt/clist/get";

    List<JSONObject> getInfos(List<String> fields) {
        Map<String, Object> params = Maps.newHashMap();
        getInfoBaseParam(params);
        params.put("fields", fields);

        String resp = HttpUtil.get(STOCK_BS_DAILY_URL, params);
        @SuppressWarnings("unchecked")
        List<JSONObject> objs = (List<JSONObject>) JSONUtil.parseObj(resp).getByPath("$.data.diff");
        return objs;
    }

    private static void getInfoBaseParam(Map<String, Object> params) {
        params.put("pn", 1);         // page number
        params.put("pz", 4000);      // page size
        params.put("po", 1);         // page
        params.put("np", 1);
        params.put("ut", "bd1d9ddb04089700cf9c27f6f7426281");
        params.put("fltt", 2);
        params.put("invt", 2);
        params.put("fid", "f3");
        params.put("fs", "m:0+t:6,m:0+t:13,m:0+t:80,m:1+t:2");
        params.put("_", new Date());
    }

}
