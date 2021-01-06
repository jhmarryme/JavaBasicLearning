package base.entity.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 
 * @author: JiaHao Wang
 * @date: 2021/1/5 15:44
 * @modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private int id;

    private String name;
}
