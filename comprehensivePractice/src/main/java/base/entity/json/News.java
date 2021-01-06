package base.entity.json;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 2021/1/5 13:34
 * @modified By:
 */
@Data
public class News {

    @SerializedName("date")
    private String date;
    @SerializedName("daycode")
    private Integer daycode;
    @SerializedName("weekday")
    private Integer weekday;
    @SerializedName("cnweekday")
    private String cnweekday;
    @SerializedName("lunaryear")
    private String lunaryear;
    @SerializedName("lunarmonth")
    private String lunarmonth;
    @SerializedName("lunarday")
    private String lunarday;
    @SerializedName("info")
    private String info;
    @SerializedName("start")
    private String start;
    @SerializedName("end")
    private String end;
    @SerializedName("holiday")
    private String holiday;
    @SerializedName("name")
    private String name;
    @SerializedName("enname")
    private String enname;
    @SerializedName("isnotwork")
    private Integer isnotwork;
    @SerializedName("vacation")
    private List<String> vacation;
    @SerializedName("remark")
    private String remark;
    @SerializedName("tip")
    private String tip;
    @SerializedName("rest")
    private String rest;

}
