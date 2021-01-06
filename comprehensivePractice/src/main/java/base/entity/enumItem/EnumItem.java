package base.entity.enumItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * description: 
 * @author JiaHao Wang
 * @date 2021/1/6 14:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EnumItem {

    //test
    VALID_NAME(400, "wjh"),

    ;

    private int id;
    private String name;
}
