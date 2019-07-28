package club.panda1024.stock.service;

import cn.hutool.core.util.StrUtil;
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
    private final static String STOCK_TRENDS_DAILY_URL = "http://push2.eastmoney.com/api/qt/stock/trends2/get";


    List<JSONObject> getInfos(List<String> fields) {
        Map<String, Object> params = Maps.newHashMap();
        getInfoBaseParam(params);
        params.put("fields", fields);

        String resp = HttpUtil.get(STOCK_BS_DAILY_URL, params);
        @SuppressWarnings("unchecked")
        List<JSONObject> objs = (List<JSONObject>) JSONUtil.parseObj(resp).getByPath("$.data.diff");
        return objs;
    }

    JSONObject getStockDetails(String code) {
        Map<String, Object> params = Maps.newHashMap();
        getTrendsBaseParam(params);
        params.put("secid", (code.startsWith("6") ? "1." : "0.") + code);

        String resp = HttpUtil.get(STOCK_TRENDS_DAILY_URL, params);
        if(StrUtil.isEmpty(resp)) {
            return null;
        }
        return (JSONObject) JSONUtil.parseObj(resp).getByPath("$.data");
    }

    private static void getTrendsBaseParam(Map<String, Object> params) {
        params.put("ndays", 1);
        params.put("ut", "fa5fd1943c7b386f172d6893dbfba10b");
        params.put("iscr", 0);
        params.put("fields1", "f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20");
        params.put("fields2", "f51,f52,f53,f54,f55,f56,f57,f58");
        params.put("_", new Date());
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
