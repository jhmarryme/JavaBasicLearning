package base.entity.json;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * description: 天行数据 节假日 按月查询 type = 2 返回结果
 *      https://www.tianapi.com/apiview/139
 * @author: JiaHao Wang
 * @date: 2021/1/5 13:33
 * @modified By:
 */
@Data
public class JiejiariResult {

    @SerializedName("code")
    private Integer code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("newslist")
    private List<News> newslist;
}
