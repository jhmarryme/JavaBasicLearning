package reading.json.gson;

import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * description:
 *      https://www.baeldung.com/tag/gson/
 * @author: JiaHao Wang
 * @date: 2021/1/5 11:28
 * @modified By:
 */
public class CompareTwoJsonTest {

    @Test
    @DisplayName("比较简单对象")
    public void whenCompareTwoSimpleObjectSuccess() {
        String str1 = "{\n" +
                "    \"customer\": {\n" +
                "        \"id\": 44521,\n" +
                "        \"fullName\": \"Emily Jenkins\",\n" +
                "        \"age\": 27\n" +
                "    }\n" +
                "}";

        String str2 = "{\n" +
                "    \"customer\": {\n" +
                "        \"id\": 44521,\n" +
                "        \"age\": 27,\n" +
                "        \"fullName\": \"Emily Jenkins\"\n" +
                "    }\n" +
                "}";
        assertEquals(JsonParser.parseString(str1), JsonParser.parseString(str2));
    }

    @Test
    @DisplayName("比较嵌套对象")
    public void whenCompareTwoNestedObjectSuccess() {
        String str1 = "{\n" +
                "  \"customer\": {\n" +
                "    \"id\": \"44521\",\n" +
                "    \"fullName\": \"Emily Jenkins\",\n" +
                "    \"age\": 27,\n" +
                "    \"consumption_info\": {\n" +
                "      \"fav_product\": \"Coke\",\n" +
                "      \"last_buy\": \"2012-04-23\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String str2 = "{\n" +
                "  \"customer\": {\n" +
                "    \"fullName\": \"Emily Jenkins\",\n" +
                "    \"id\": \"44521\",\n" +
                "    \"age\": 27,\n" +
                "    \"consumption_info\": {\n" +
                "      \"last_buy\": \"2012-04-23\",\n" +
                "      \"fav_product\": \"Coke\"\n" +
                "   }\n" +
                "  }\n" +
                "}";
        assertEquals(JsonParser.parseString(str1), JsonParser.parseString(str2));

    }

    @Test
    @DisplayName("比较基础类型数组")
    public void whenCompareTwoArraySuccess() {

        String str1 = "[10, 20, 30]";
        String str2 = "[20, 10, 30]";

        assertTrue(JsonParser.parseString(str1).isJsonArray());

        // 数组对顺序敏感
        assertNotEquals(JsonParser.parseString(str1), JsonParser.parseString(str2));
    }

    @Test
    @DisplayName("比较对象类型数组")
    public void whenCompareTwoObjectArraySuccess() {
        String str1 = "[\n" +
                "  {\n" +
                "    \"date\": \"2021-01-31\",\n" +
                "    \"daycode\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"daycode\": 3,\n" +
                "    \"date\": \"2021-01-21\"\n" +
                "  }\n" +
                "]";

        String str2 = "[\n" +
                "  {\n" +
                "    \"daycode\": 2,\n" +
                "    \"date\": \"2021-01-31\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"daycode\": 3,\n" +
                "    \"date\": \"2021-01-21\"\n" +
                "  }\n" +
                "]";

        // 同样对顺序敏感
        assertEquals(JsonParser.parseString(str1), JsonParser.parseString(str2));

    }

}
