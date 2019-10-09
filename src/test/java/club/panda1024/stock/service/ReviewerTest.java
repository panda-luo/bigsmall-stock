package club.panda1024.stock.service;

import club.panda1024.stock.model.entity.Review;
import club.panda1024.stock.model.entity.Reviewer;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.ImageUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ReviewerTest {

    public static final String appId = "14905565";
    public static final String appKey = "BwNo0PdmtbGVTbGqtWQ0yrBb";
    public static final String secretKey = "rqjLqjLwIPIQTKIUstz0iCX8NreDYZPW";

    public int year = 2019;
    public boolean lock = false;
    public String HUNANREN_URL = "https://www.taoguba.com.cn/moreTopic?userID=444409";
    public String DEMO_URL = "https://www.taoguba.com.cn/moreTopic?pageNum=6&pageNo=2&sortFlag=T&userID=1278795";
    public String DEMO_URL_PRE = "https://www.taoguba.com.cn/moreTopic?pageNum=6&pageNo=";
    public String DEMO_LOL_URL_SUF = "&sortFlag=T&userID=1278795";
    public String DEMO_HUNANREN_URL_SUF = "&sortFlag=T&userID=444409";
    public String DEMO_URL_SUF = "&sortFlag=T&userID=444409";

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewerService reviewerService;


    @Test
    public void getHtml() throws InterruptedException {
//        String s = HttpUtil.get(DEMO_URL);

        for(int i = 1; i <= 6; i++) {
            String currentUrl = DEMO_URL_PRE + i + DEMO_URL_SUF;

            String s = HttpRequest
                    .get(currentUrl)
                    .header("cookie", "UM_distinctid=16ca5ff9fa02a2-0292cadca45294-38617701-13c680-16ca5ff9fa2391; tgbuser=2529235; tgbpwd=1F0ABE4C2AB4jc4p0lzdz4ysxs; Hm_lvt_cc6a63a887a7d811c92b7cc41c441837=1566153089,1567736568; notActiveUserIDPC=88348424; JSESSIONID=9b57a195-abe1-4569-ae42-8c80fbd7d560; CNZZDATA1574657=cnzz_eid%3D329063898-1566151686-https%253A%252F%252Fwww.baidu.com%252F%26ntime%3D1568167411; Hm_lpvt_cc6a63a887a7d811c92b7cc41c441837=1568169498")
                    .execute().body();

            Pattern p = Pattern.compile("<a href=\"([^']*)\"[^>]*>([^<]*)</a>");
            Matcher m = p.matcher(s);

            while (m.find()) {
                if (m.group(1).contains("Article")) {
                    try {
                        String url = "https://www.taoguba.com.cn/" + StrUtil.cleanBlank(m.group(1));
                        String title = StrUtil.removeAll(StrUtil.cleanBlank(m.group(2)), "&nbsp;");
                        Date date = extractDate(title);

                        Review review = new Review();
                        review.setUrl(url)
                                .setTitle(title)
                                .setReviewerId(1)
                                .setTs(date);

                        reviewService.saveOrUpdate(review);
                        log.info("ReviewProvider save review success with [{}]", review.getTitle());
//                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        log.error("Error while do save review.");
                    }
                }
            }
        }
    }


    @Test
    public void getHunanrenImage() {
        AipOcr ocr = new AipOcr(appId, appKey, secretKey);

        InputStream in = URLUtil.getStream(URLUtil.url("https://image.taoguba.com.cn/img/2019/09/24/qfhiu83fxv1o.png_max.png"));
        byte[] bytes = IoUtil.readBytes(in);
        String s = HttpUtil.get("https://image.taoguba.com.cn/img/2019/09/24/qfhiu83fxv1o.png_760w.png");
        JSONObject obj = ocr.accurateGeneral(bytes, new HashMap<>());
//        JSONObject obj = ocr.basicGeneralUrl("https://image.taoguba.com.cn/img/2019/09/24/qfhiu83fxv1o.png_760w.png", new HashMap<>());
//        System.out.println(obj);
        System.out.println(obj);
    }

    public Date extractDate(String title) {
        String dateStr = StrUtil.sub(title, title.lastIndexOf("：") + 1, title.indexOf("每"));
        int month = DateUtil.month(DateUtil.parse(dateStr, "MM.dd"));
        if(month == 11 && !lock) {
            lock = true;
            year--;
        }
        if(month == 10 && lock) {
            lock = false;
        }
        log.info("Month:{} - Title:{}", month, title);
        return DateUtil.parse(year + dateStr, "yyyyMM.dd");
    }


    @Test
    public void getOneTest() {
        List<Review> reviews = reviewService.list();

        for(Review review : reviews) {
            String url = review.getUrl();

            if(StrUtil.isEmpty(review.getContent())) {
                String content = HttpUtil.get(url);

                Pattern p = Pattern.compile("<!--主贴内容开始-->(.*?)<!--主贴内容结束-->");
                Matcher m = p.matcher(StrUtil.cleanBlank(content));
                while(m.find()) {
                    String group = m.group(1);
                    review.setContent(StrUtil.removeAll(HtmlUtil.cleanHtmlTag(group), "&nbsp;"));
                }
                log.info("ReviewProvider save review content success with [{}]", review.getTitle());
            }
        }
        reviewService.saveOrUpdateBatch(reviews);
    }


    @Test
    public void getOneContent() {
        List<Review> reviews = reviewService.list();

        for(Review review : reviews) {
            System.out.println(review.getContent());
        }
    }


    @Test
    public void addOneReviewerTest() {
        Reviewer reviewer = new Reviewer();
        reviewer.setId(1)
                .setName("LOL123")
                .setUrl("https://www.taoguba.com.cn/blog/1278795")
                .setSource(1);

        reviewerService.save(reviewer);
    }

}
