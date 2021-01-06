package reading.json.gson;

import base.entity.json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * description:
 *      `@SerializedName(value="字段名", alternate="别名")`
 *
 *      https://www.baeldung.com/tag/gson/
 * @author: JiaHao Wang
 * @date: 2021/1/5 12:48
 * @modified By:
 */
public class DeserializedTest {

    @Test
    @DisplayName("对象的反序列化")
    public void whenObjectDeserializedSuccess() {
        Gson gson = new GsonBuilder().create();
        Weather weather = gson.fromJson("{\n"
                + "  \"location\": \"London\",\n"
                + "  \"temp\": 15,\n"
                + "  \"weather\": \"Cloudy\"\n"
                + "}", Weather.class);

        assertEquals("London", weather.getLocation());
        assertEquals("Cloudy", weather.getOutlook());
        assertEquals(15, weather.getTemp());

        // 使用别名也可以反序列化成功
        weather = gson.fromJson("{\n"
                + "  \"place\": \"Lisbon\",\n"
                + "  \"temperature\": 35,\n"
                + "  \"outlook\": \"Sunny\"\n"
                + "}", Weather.class);

        assertEquals("Lisbon", weather.getLocation());
        assertEquals("Sunny", weather.getOutlook());
        assertEquals(35, weather.getTemp());
    }


    @Test
    @DisplayName("包含List的对象反序列化")
    public void whenJiejiariDeserializedSuccess() {
        Gson gson = new Gson();
        JiejiariResult result = gson.fromJson(getJiejiariResult(), JiejiariResult.class);

        assertEquals(200, result.getCode());
        assertEquals("success", result.getMsg());
        assertEquals("2021-01-01", result.getNewslist().get(0).getDate());
    }


    @Test
    @DisplayName("List对象反序列化")
    public void whenListDeserializedSuccess() {
        Gson gson = new Gson();

        String inputString = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";
        List<Person> inputList = Arrays.asList(new Person(1, "name1"), new Person(2, "name2"));

        // Gson的TypeToken来确定要反序列化的正确类型
        Type listOfMyClassObject = new TypeToken<ArrayList<Person>>() {}.getType();

        List<Person> outputList = gson.fromJson(inputString, listOfMyClassObject);

        assertEquals(inputList, outputList);
    }

    @Test
    @DisplayName("List对象反序列化-失败")
    public void whenListDeserializedFailed() {
        String inputString = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";

        Gson gson = new Gson();

        assertThrows(
                ClassCastException.class,
                () -> {
                    List<Person> outputList = gson.fromJson(inputString, ArrayList.class);
                    assertEquals(1, outputList.get(0).getId());
                },
                ""
        );
    }
    
    @Test
    @DisplayName("多态对象List反序列化")
    public void whenPolyMorphicObjectListDeserializedSuccess() {
        // TODO https://www.baeldung.com/gson-list
    }

    public String getJiejiariResult() {
        return "{\n" +
                "  \"code\": 200,\n" +
                "  \"msg\": \"success\",\n" +
                "  \"newslist\": [\n" +
                "    {\n" +
                "      \"date\": \"2021-01-01\",\n" +
                "      \"daycode\": 1,\n" +
                "      \"weekday\": 5,\n" +
                "      \"cnweekday\": \"星期五\",\n" +
                "      \"lunaryear\": \"庚子\",\n" +
                "      \"lunarmonth\": \"冬月\",\n" +
                "      \"lunarday\": \"十八\",\n" +
                "      \"info\": \"节假日\",\n" +
                "      \"start\": 0,\n" +
                "      \"end\": 2,\n" +
                "      \"holiday\": \"1月1号\",\n" +
                "      \"name\": \"元旦节\",\n" +
                "      \"enname\": \"New Year's Day\",\n" +
                "      \"isnotwork\": 1,\n" +
                "      \"vacation\": [\n" +
                "        \"2021-01-01\",\n" +
                "        \"2021-01-02\",\n" +
                "        \"2021-01-03\"\n" +
                "      ],\n" +
                "      \"remark\": \"\",\n" +
                "      \"tip\": \"1月1日放假，共3天。\",\n" +
                "      \"rest\": \"2020年12月28日至2020年12月31日请假四天，与周末连休可拼七天长假。\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"date\": \"2021-01-02\",\n" +
                "      \"daycode\": 1,\n" +
                "      \"weekday\": 6,\n" +
                "      \"cnweekday\": \"星期六\",\n" +
                "      \"lunaryear\": \"庚子\",\n" +
                "      \"lunarmonth\": \"冬月\",\n" +
                "      \"lunarday\": \"十九\",\n" +
                "      \"info\": \"节假日\",\n" +
                "      \"start\": 0,\n" +
                "      \"end\": 2,\n" +
                "      \"holiday\": \"1月1号\",\n" +
                "      \"name\": \"元旦节\",\n" +
                "      \"enname\": \"\",\n" +
                "      \"isnotwork\": 1,\n" +
                "      \"vacation\": [\n" +
                "        \"2021-01-01\",\n" +
                "        \"2021-01-02\",\n" +
                "        \"2021-01-03\"\n" +
                "      ],\n" +
                "      \"remark\": \"\",\n" +
                "      \"tip\": \"1月1日放假，共3天。\",\n" +
                "      \"rest\": \"2020年12月28日至2020年12月31日请假四天，与周末连休可拼七天长假。\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

}
