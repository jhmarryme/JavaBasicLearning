package cn.jhmarryme.excel;

import cn.jhmarryme.excel.duty.entity.News;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class JsonTest {

    @Test
    void testJson() {
        String json = "{\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"success\",\n" +
                "    \"newslist\": [\n" +
                "        {\n" +
                "            \"date\": \"2021-01-01\",\n" +
                "            \"daycode\": 1,\n" +
                "            \"weekday\": 5,\n" +
                "            \"cnweekday\": \"星期五\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"十八\",\n" +
                "            \"info\": \"节假日\",\n" +
                "            \"start\": 0,\n" +
                "            \"end\": 2,\n" +
                "            \"holiday\": \"1月1号\",\n" +
                "            \"name\": \"元旦节\",\n" +
                "            \"enname\": \"New Year's Day\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": [\n" +
                "                \"2021-01-01\",\n" +
                "                \"2021-01-02\",\n" +
                "                \"2021-01-03\"\n" +
                "            ],\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"1月1日放假，共3天。\",\n" +
                "            \"rest\": \"2020年12月28日至2020年12月31日请假四天，与周末连休可拼七天长假。\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-02\",\n" +
                "            \"daycode\": 1,\n" +
                "            \"weekday\": 6,\n" +
                "            \"cnweekday\": \"星期六\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"十九\",\n" +
                "            \"info\": \"节假日\",\n" +
                "            \"start\": 0,\n" +
                "            \"end\": 2,\n" +
                "            \"holiday\": \"1月1号\",\n" +
                "            \"name\": \"元旦节\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": [\n" +
                "                \"2021-01-01\",\n" +
                "                \"2021-01-02\",\n" +
                "                \"2021-01-03\"\n" +
                "            ],\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"1月1日放假，共3天。\",\n" +
                "            \"rest\": \"2020年12月28日至2020年12月31日请假四天，与周末连休可拼七天长假。\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-03\",\n" +
                "            \"daycode\": 1,\n" +
                "            \"weekday\": 0,\n" +
                "            \"cnweekday\": \"星期日\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"二十\",\n" +
                "            \"info\": \"节假日\",\n" +
                "            \"start\": 0,\n" +
                "            \"end\": 2,\n" +
                "            \"holiday\": \"1月1号\",\n" +
                "            \"name\": \"元旦节\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": [\n" +
                "                \"2021-01-01\",\n" +
                "                \"2021-01-02\",\n" +
                "                \"2021-01-03\"\n" +
                "            ],\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"1月1日放假，共3天。\",\n" +
                "            \"rest\": \"2020年12月28日至2020年12月31日请假四天，与周末连休可拼七天长假。\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-04\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 1,\n" +
                "            \"cnweekday\": \"星期一\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿一\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-05\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 2,\n" +
                "            \"cnweekday\": \"星期二\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿二\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-06\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 3,\n" +
                "            \"cnweekday\": \"星期三\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿三\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-07\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 4,\n" +
                "            \"cnweekday\": \"星期四\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿四\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-08\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 5,\n" +
                "            \"cnweekday\": \"星期五\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿五\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-09\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 6,\n" +
                "            \"cnweekday\": \"星期六\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿六\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-10\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 0,\n" +
                "            \"cnweekday\": \"星期日\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿七\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-11\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 1,\n" +
                "            \"cnweekday\": \"星期一\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿八\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-12\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 2,\n" +
                "            \"cnweekday\": \"星期二\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"冬月\",\n" +
                "            \"lunarday\": \"廿九\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-13\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 3,\n" +
                "            \"cnweekday\": \"星期三\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初一\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-14\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 4,\n" +
                "            \"cnweekday\": \"星期四\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初二\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-15\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 5,\n" +
                "            \"cnweekday\": \"星期五\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初三\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-16\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 6,\n" +
                "            \"cnweekday\": \"星期六\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初四\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-17\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 0,\n" +
                "            \"cnweekday\": \"星期日\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初五\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-18\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 1,\n" +
                "            \"cnweekday\": \"星期一\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初六\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-19\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 2,\n" +
                "            \"cnweekday\": \"星期二\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初七\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-20\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 3,\n" +
                "            \"cnweekday\": \"星期三\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初八\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-21\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 4,\n" +
                "            \"cnweekday\": \"星期四\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初九\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-22\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 5,\n" +
                "            \"cnweekday\": \"星期五\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"初十 \",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-23\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 6,\n" +
                "            \"cnweekday\": \"星期六\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十一\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-24\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 0,\n" +
                "            \"cnweekday\": \"星期日\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十二\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-25\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 1,\n" +
                "            \"cnweekday\": \"星期一\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十三\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-26\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 2,\n" +
                "            \"cnweekday\": \"星期二\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十四\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-27\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 3,\n" +
                "            \"cnweekday\": \"星期三\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十五\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-28\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 4,\n" +
                "            \"cnweekday\": \"星期四\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十六\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-29\",\n" +
                "            \"daycode\": 0,\n" +
                "            \"weekday\": 5,\n" +
                "            \"cnweekday\": \"星期五\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十七\",\n" +
                "            \"info\": \"工作日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 0,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-30\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 6,\n" +
                "            \"cnweekday\": \"星期六\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十八\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"date\": \"2021-01-31\",\n" +
                "            \"daycode\": 2,\n" +
                "            \"weekday\": 0,\n" +
                "            \"cnweekday\": \"星期日\",\n" +
                "            \"lunaryear\": \"庚子\",\n" +
                "            \"lunarmonth\": \"腊月\",\n" +
                "            \"lunarday\": \"十九\",\n" +
                "            \"info\": \"双休日\",\n" +
                "            \"start\": \"\",\n" +
                "            \"end\": \"\",\n" +
                "            \"holiday\": \"\",\n" +
                "            \"name\": \"\",\n" +
                "            \"enname\": \"\",\n" +
                "            \"isnotwork\": 1,\n" +
                "            \"vacation\": \"\",\n" +
                "            \"remark\": \"\",\n" +
                "            \"tip\": \"\",\n" +
                "            \"rest\": \"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";


//        System.out.println("json = " + json);


        Gson gson = new Gson();

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("newslist").getAsJsonArray();
        List<News> list = gson.fromJson(jsonArray.toString(), List.class);

        List<News> news = JSONObject.parseArray(jsonArray.toString(), News.class);


        System.out.println();
    }


    @Test
    public void localDateTest() {

        System.out.println("LocalDate.now().toString() = " + LocalDate.now().toString());

        LocalDate now = LocalDate.now();
        System.out.println("now.equals(LocalDate.of(2021, 1, 3)) = " + "2021-01-03".equals(now.toString()));


        Integer integer = Integer.valueOf("1");

        System.out.println(integer == 1);
    }
}
