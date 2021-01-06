package reading.effectiveJava._3_singleton;

import lombok.*;

/**
 * description:
 * 3. 使用私有构造方法或枚类实现 Singleton 属性
 *      这里只使用了枚举实现
 * @Author: Wjh
 * @Date: 2020/8/26 10:20
 * @Modified By:
 */
@AllArgsConstructor
@NoArgsConstructor
public enum SingletonWithEnum {
    /**
     * 实例对象
     */
    INSTANCE;

    public String leaveTheBuilding() {
        // do something
        return "SingletonWithEnum{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private String name;

    private int age;

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * 设置
     *
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
