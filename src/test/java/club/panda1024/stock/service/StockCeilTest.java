package club.panda1024.stock.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockCeilTest {

    private String content;

    @Before
    public void setContent() {
        content = "重发一下，更正了几处错误）【9.25星期三】&nbsp;&nbsp;湖南人涨停复盘&nbsp;&nbsp;<br>&quot;涨停27家 连板8家 首板19家 <br>炸板12家&nbsp;&nbsp;跌停20家<br>涨停金额:86亿 炸板金额:77亿<br>首板金额:55亿 连板金额:31亿<br>科创板金额:72亿&quot;&nbsp;&nbsp;&nbsp;&nbsp;<br>【市场连板股】8只 31亿&nbsp;&nbsp;&nbsp;&nbsp;<br>代码 名称 末次时间 连板数 原因<br>002552 宝鼎科技 9:25 6 公告<br>002316 亚联发展 9:39 3 区块链+华为+物联网<br>300510 金冠股份 10:24 3 区块链+智能电网<br>300293 蓝英装备 11:30 3 光刻机<br>603602 纵横通信 14:52 3 华为+5G<br>603477 振静股份 9:25 2 公告<br>600122 宏图高科 9:30 2 区块链+消费电子<br>600981 汇鸿集团 14:27 2 区块链<br>【区块链/数字货币】10只 44亿&nbsp;&nbsp;&nbsp;&nbsp;<br>600122 宏图高科 9:30 2 区块链+消费电子<br>002316 亚联发展 9:39 3 区块链+华为+物联网<br>002453 华软科技 9:44 1 区块链+金融科技<br>600986 科达股份 9:47 1 区块链+华为<br>300202 聚龙股份 9:49 1 区块链+数字货币<br>300510 金冠股份 10:24 3 区块链+智能电网<br>000607 华媒控股 11:13 1 区块链<br>002386 天原集团 13:31 1 区块链+钛白粉<br>002668 奥马电器 14:25 1 区块链+数字货币<br>600981 汇鸿集团 14:27 2 区块链<br>【科技】4只 17亿&nbsp;&nbsp;&nbsp;&nbsp;<br>000701 厦门信达 9:41 1 物联网<br>300293 蓝英装备 11:30 3 光刻机<br>603602 纵横通信 14:52 3 华为+5G<br>300338 开元股份 14:56 1 物联网<br>【公告】5只 2亿&nbsp;&nbsp;&nbsp;&nbsp;<br>603477 振静股份 9:25 2 公告复牌收购<br>002552 宝鼎科技 9:25 6 公告股权变更<br>002756 永兴材料 9:25 1 公告研发新材料<br>002693 双成药业 9:30 1 公告科研项目获批<br>002893 华通热力 9:34 1 公告收购<br>【其他】&nbsp;&nbsp;&nbsp;&nbsp;<br>603956 威派格 9:49 1 次新股<br>002943 宇晶股份 10:57 1 次新股<br>603777 来伊份 13:35 1 食品<br>603317 天味食品 14:56 1 食品<br>600353 旭光股份 10:30 1 军工<br>002676 顺威股份 14:45 1 家电<br>300220 金运激光 14:49 1 3D打印<br>603026 石大胜华 14:52 1 新能源汽车<br>【涨停炸板】12只 77亿&nbsp;&nbsp;&nbsp;&nbsp;<br>代码 名称 首次时间 涨跌幅 原因<br>002466 天齐锂业 13:19 9.97% 新能源汽车<br>300386 飞天诚信 14:37 8.36% 区块链+数字货币<br>002169 智光电气 14:34 6.89% 国产芯片<br>300721 怡达股份 9:35 6.79% 国产芯片<br>002845 同兴达 10:13 6.35% 华为+OLED<br>000007 全新好 10:48 6.18% 深圳+金融科技<br>002671 龙泉股份 14:17 5.93% 其他<br>300722 新余国科 10:02 5.67% 军工<br>000909 数源科技 10:04 4.76% 无人驾驶<br>300329 海伦钢琴 9:30 4.25% 二胎<br>600517 置信电气 9:25 1.87% 公告<br>002886 沃特股份 9:38 -0.32% 5G+华为";
    }

    @Autowired
    private StockCeilService stockCeilService;

    @Test
    public void getContent() {
        stockCeilService.getContent();
    }

    @Test
    public void extractContent() {
        stockCeilService.extractContent(content);
    }

}
