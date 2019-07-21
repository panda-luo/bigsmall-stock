package club.panda1024.stock.griper;

import club.panda1024.stock.griper.helper.EaHelper;
import club.panda1024.stock.model.entity.StockDetail;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockInfoGripper {


    public List<StockDetail> basicStockInfoGripper() {
        List<StockDetail> details = Lists.newArrayList();

        String resp = HttpUtil.get(EaHelper.EA_BASE_STOCK_URL, EaHelper.defaultGripperGetParam());

        @SuppressWarnings("unchecked")
        List<JSONObject> bigStockInfos = (List<JSONObject>) JSONUtil.parseObj(resp).getByPath("$.data.diff");

        for(JSONObject obj : bigStockInfos) {
            StockDetail stockDetail = new StockDetail();
            stockDetail.setCode((String) obj.get("f12"))
                    .setName((String) obj.get("f14"))
                    .setReleaseDate(String.valueOf(obj.get("f26")))
                    .setPrice(String.valueOf(obj.get("f2")))
                    .setZhangdiefu(String.valueOf(obj.get("f3")))
                    .setCity((String) obj.get("f102"))
                    .setChengjiaoe(String.valueOf(obj.get("f6")))
                    .setHuanshoulv(String.valueOf(obj.get("f8")))
                    .setPe(String.valueOf(obj.get("f9")));
            details.add(stockDetail);
        }
        return details;
    }




}
