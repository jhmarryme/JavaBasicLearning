package javaBase.enumBase;

import base.entity.enumItem.EnumItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * description: 
 * @author JiaHao Wang
 * @date 2021/1/6 13:34
 */
public class EnumTestDrive {

    @Test
    @DisplayName("测试枚举类型字段值")
    public void whenGetEnumValueSuccess() {
        // 当没有重写toString方法时都会打印 VALID_NAME
        System.out.println(EnumItem.VALID_NAME);
        System.out.println(EnumItem.VALID_NAME.toString());
    }
}
