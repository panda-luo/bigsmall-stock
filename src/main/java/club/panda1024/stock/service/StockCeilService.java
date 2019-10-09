package club.panda1024.stock.service;

import club.panda1024.stock.exception.BusinessException;
import club.panda1024.stock.mapper.StockCeilMapper;
import club.panda1024.stock.model.entity.StockCeil;
import club.panda1024.stock.util.ValidHelper;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Slf4j
@Service
public class StockCeilService extends ServiceImpl<StockCeilMapper, StockCeil> {

    private String ceilUrl;
    private JSONObject ceilContent;

    private static final String CEIL_URL_PRE = "https://weibo.com/p/aj/mblog/getlongtext?ajwvr=6&mid=";
    private static final String CEIL_URL_SUF = "&is_settop&is_sethot&is_setfanstop&is_setyoudao&__rnd=1569719890843";

    public void assembleUrl() {
        String mid = "4420463726148811";
        ceilUrl = StrUtil.concat(false, CEIL_URL_PRE, mid, CEIL_URL_SUF);
    }


    public void validCeilUrl() {

        ceilContent = JSONUtil.parseObj(
                HttpRequest
                        .get(ceilUrl)
                        .header("cookie", "UOR=ent.ifeng.com,widget.weibo.com,ent.ifeng.com; TC-V5-G0=0cd4658437f38175b9211f1336161d7d; login_sid_t=7720999105a27234edcabbf275ce63bb; cross_origin_proto=SSL; Ugrow-G0=5c7144e56a57a456abed1d1511ad79e8; _s_tentry=-; Apache=9937550114097.865.1569321378501; SINAGLOBAL=9937550114097.865.1569321378501; ULV=1569321378518:1:1:1:9937550114097.865.1569321378501:; SSOLoginState=1569321397; un=cristopanda@gmail.com; wvr=6; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFqSfh-6yLYs5Uifcw72ZUE5JpX5KMhUgL.Fo2c1hqXehqfSh52dJLoIpUT9g4VwCH81F-ReE-4SbH81FHFxCHFxBtt; ALF=1601255728; SCF=AmSwVVWOWWfryEFNxirIWcvxsdafJ0TCWqBFu21v2vpsTLoQ9g6OWUUwc3u1_BsDSIUte1uMXr7iVDgbZGDJoGA.; SUB=_2A25wlHXhDeRhGedI41QV8CjJzzyIHXVT4OAprDV8PUNbmtBeLWLAkW9NVrbRfYzpHRsJ6mhKSUfmQpQKBKSvUUAR; SUHB=0la0cUa6gGGxYd; wb_view_log_1686406540=1440*9001; TC-Page-G0=7f6863db1952ff8adac0858ad5825a3b|1569719770|1569719740; webim_unReadCount=%7B%22time%22%3A1569719886231%2C%22dm_pub_total%22%3A0%2C%22chat_group_client%22%3A0%2C%22allcountNum%22%3A0%2C%22msgbox%22%3A0%7D")
                        .execute()
                        .body()
        );
        Object signal = JSONUtil.getByPath(ceilContent, "$.code");
        if (!signal.toString().equals("100000")) {
            throw new BusinessException("url已失效");
        }
    }


    public void getContent() {
        assembleUrl();
        validCeilUrl();
        String html = (String) JSONUtil.getByPath(ceilContent, "$.data.html");
        log.info("StockCeil html is = {}", html);

    }


    public void extractContent(String content) {
        String[] splits = StrUtil.split(content, "<br>");
        List<StockCeil> stockCeils = Lists.newArrayList();
        for (String str : splits) {

            String[] sp2 = StrUtil.split(str, " ");

            if (sp2.length == 5 && ValidHelper.validStockCode(sp2[0])) {
                StockCeil stockCeil = new StockCeil();
                if (sp2[3].contains("%")) {
                    stockCeil.setCode(sp2[0])
                            .setName(sp2[1])
                            .setFirstTs(DateUtil.parse(StrUtil.concat(false, String.valueOf(DateUtil.thisYear()), ":", sp2[2]), "yyyy:hh:mm"))
                            .setQuoteChangeRate(Double.valueOf(StrUtil.removeAll(sp2[3], "%")))
                            .setTag(StrUtil.replace(sp2[4], "+", ","));
                    stockCeils.add(stockCeil);

                } else {
                    stockCeil.setCode(sp2[0])
                            .setName(sp2[1])
                            .setLastTs(DateUtil.parse(StrUtil.concat(false, String.valueOf(DateUtil.thisYear()), ":", sp2[2]), "yyyy:hh:mm"))
                            .setCount(Integer.valueOf(sp2[3]))
                            .setTag(StrUtil.replace(sp2[4], "+", ","));
                    stockCeils.add(stockCeil);
                }
            }
        }

        stockCeils.forEach(e -> log.info("StockCeil is={}.", e));
    }
}
