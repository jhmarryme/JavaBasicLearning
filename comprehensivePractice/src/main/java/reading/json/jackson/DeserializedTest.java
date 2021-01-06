package reading.json.jackson;

import base.entity.json.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * description:
 *      `@JsonIgnore` 此注解用于属性上 作用是进行JSON操作时忽略该属性
 *      `@JsonFormat` 此注解用于属性上 作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
 *      `@JsonProperty` 此注解用于属性上 作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")
 *      `@JsonAlias` 此注解用于属性上 指定别名
 * @author: JiaHao Wang
 * @date: 2021/1/5 12:25
 * @modified By:
 */
public class DeserializedTest {
    
    @SneakyThrows
    @Test
    @DisplayName("反序列化, 支持JsonProperty/JsonAlias混合使用, 也可以不指定")
    public void whenDeserializedSuccess() {
        String str1 = "{\n"
                + "  \"location\": \"London\",\n"
                + "  \"temp\": 15,\n"
                + "  \"outlook\": \"Cloudy\"\n"
                + "}";
        String str2 = "{\n"
                + "  \"place\": \"Lisbon\",\n"
                + "  \"temperature\": 35,\n"
                + "  \"weather\": \"Sunny\"\n"
                + "}";

        ObjectMapper mapper = new ObjectMapper();

        Weather weather = mapper.readValue(str1, Weather.class);
        assertEquals("London", weather.getLocation());
        assertEquals("Cloudy", weather.getOutlook());
        assertEquals(15, weather.getTemp());

        weather = mapper.readValue(str2, Weather.class);
        assertEquals("Lisbon", weather.getLocation());
        assertEquals("Sunny", weather.getOutlook());
        assertEquals(35, weather.getTemp());

    }

}
