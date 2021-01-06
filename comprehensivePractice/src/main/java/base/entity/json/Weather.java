package base.entity.json;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: json序列化 测试对象
 * @author: JiaHao Wang
 * @date: 2021/1/5 12:34
 * @modified By:
 */
@Data
public class Weather {

    @JsonProperty("location")
    @JsonAlias("place")
    @SerializedName(value="location", alternate="place")
    private String location;

    @JsonProperty("temp")
    @JsonAlias("temperature")
    @SerializedName(value="temp", alternate="temperature")
    private int temp;

    @SerializedName(value="outlook", alternate="weather")
    @JsonProperty("outlook")
    @JsonAlias("weather")
    private String outlook;

}
