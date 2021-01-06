package cn.jhmarryme.excel.duty.service;

import cn.jhmarryme.excel.duty.entity.Coder;
import cn.jhmarryme.excel.duty.entity.CoderExcel;
import cn.jhmarryme.excel.duty.entity.News;
import cn.jhmarryme.excel.duty.entity.vo.DutyInfoRequestVO;
import cn.jhmarryme.excel.duty.util.RedisUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 1/2/21 12:36 PM
 * @modified By:
 */
@Service
public class DutyInfoService {

    @Autowired
    private RedisUtil redisUtil;

    public List<Coder> getCoderInfo() {
        ArrayList<Coder> list = new ArrayList<>();
        list.add(new Coder(1, "徐苏北"));
        list.add(new Coder(2, "张原茂"));
        list.add(new Coder(3, "吴四维"));
        list.add(new Coder(4, "王家豪"));
        list.add(new Coder(5, "田秀华"));
        list.add(new Coder(6, "屠琦"));
        list.add(new Coder(7, "胡成星"));
        list.add(new Coder(8, "陈俊安"));
        list.add(new Coder(9, "谢希豪"));
        list.add(new Coder(10, "刘俊杰"));
        list.add(new Coder(11, "雷清英"));
        list.add(new Coder(12, "程鑫凯"));

        return list;
    }

    public List<CoderExcel> createCoderExcelList(DutyInfoRequestVO requestVO, HttpServletResponse response) throws IOException {

        // 方法3 如果写到不同的sheet 不同的对象
        ExcelWriter excelWriter = null;

        LocalDate startDate = requestVO.getStartDate();
        LocalDate endDate = requestVO.getEndDate();
        if (startDate.isAfter(endDate)) {
            // 异常
            return null;
        }

        try {
            // 这里 指定文件
            excelWriter = EasyExcel.write(response.getOutputStream(), CoderExcel.class).build();

            int index = 0;
            int sheetNo = 0;
            while (startDate.isBefore(endDate)) {
                index = printCoderExcelByMonth(excelWriter, sheetNo, startDate, index);
                sheetNo++;
                startDate = startDate.with(TemporalAdjusters.firstDayOfNextMonth());
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        return null;
    }

    public int printCoderExcelByMonth(ExcelWriter excelWriter, int sheetNo, LocalDate date, int index) {

        LocalDate startDate = date;
        int currentMonth = startDate.getMonth().getValue();

        List<Coder> coderList = getCoderInfo();
        List<CoderExcel> coderExcelList = new ArrayList<>();

        // 获取redis数据
        String item = date.getYear() + "-" + currentMonth;
        JSONArray hget = (JSONArray) redisUtil.hget(String.valueOf(date.getYear()), item);

        List<News> newsList = JSONObject.parseArray(hget.toString(), News.class);

        while (startDate.getMonth().getValue() == currentMonth) {

            // 处理节假日
            DayOfWeek dayOfWeek = startDate.getDayOfWeek();
            News news = getCurrentPerDateInfo(startDate, newsList).orElseThrow();
            if (news.getIsnotwork() == 0) {
                coderExcelList.add(CoderExcel
                        .builder()
                        .date(Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                        .name(coderList.get(index).getName())
                        .whatDay(news.getCnweekday())
                        .remark(news.getTip())
                        .build()
                );
                if (++index == coderList.size()) {
                    index = 0;
                }
            }
            startDate = startDate.plusDays(1);
        }

        WriteSheet writeSheet = EasyExcel.writerSheet(sheetNo, currentMonth + "月").head(CoderExcel.class).build();
        excelWriter.write(coderExcelList, writeSheet);

        return index;
    }


    public Optional<News> getCurrentPerDateInfo(LocalDate localDate, List<News> list) {
        return list.stream().filter(news -> news.getDate().equals(localDate.toString())).findFirst();
    }

    public void initYears(int year) {
        String httpUrl = "http://api.tianapi.com/txapi/jiejiari/index";

        LocalDate startDate = LocalDate.of(year, 1, 1);

        LocalDate endDate = startDate.with(TemporalAdjusters.firstDayOfNextYear());

        while (startDate.isBefore(endDate)) {
            String date = startDate.getYear() + "-" + startDate.getMonth().getValue();
            String httpArg = "key=1c5835b88ebba7b674891d603c69e72f&date=" + date + "&type=2";
            String jsonResult = request(httpUrl, httpArg);

            List<News> newsList = handleJsonResult(jsonResult);
            redisUtil.hset(String.valueOf(year), date, newsList);
            startDate = startDate.with(TemporalAdjusters.firstDayOfNextMonth());
            System.out.println(date + "\n" + jsonResult);
            System.out.println("=======================");

        }
    }

    private List<News> handleJsonResult(String jsonResult) {
        Gson gson = new Gson();

        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("newslist").getAsJsonArray();
        List<News> list = gson.fromJson(jsonArray.toString(), List.class);

        List<News> news = JSONObject.parseArray(jsonArray.toString(), News.class);

        return news;
    }

    public void getYearsData(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);

        LocalDate endDate = startDate.with(TemporalAdjusters.firstDayOfNextYear());
        while (startDate.isBefore(endDate)) {
            String date = startDate.getYear() + "-" + startDate.getMonth().getValue();
            List<News> hget = (List<News>) redisUtil.hget(String.valueOf(year), date);

            startDate = startDate.with(TemporalAdjusters.firstDayOfNextMonth());
            System.out.println("hget = " + hget);
        }
    }


    /**
     * @param httpUrl
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
