package reading.json.gson;

import base.entity.json.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 2021/1/5 17:41
 * @modified By:
 */
public class SerializedTest {

    @Test
    @DisplayName("序列化 - 保留空值")
    public void whenCancelIgnoreNullValuesSuccess() {

        Person person = Person.builder().id(1).build();


        Gson gson = new GsonBuilder().serializeNulls().create();

        System.out.println(gson.toJson(person));

    }

    @Test
    @DisplayName("保留static transient volatile字段")
    public void whenKeepStaticAndTransientSuccess() {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC,
                Modifier.VOLATILE).create();
    }

    @Test
    @DisplayName("序列化 - 忽略特定字段 - 只保留 带有@Expose注解的字段")
    public void whenIgnoreSpecificFieldsWithExposeSuccess() {
        Person person = Person.builder().id(1).build();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        System.out.println(gson.toJson(person));

    }
}
