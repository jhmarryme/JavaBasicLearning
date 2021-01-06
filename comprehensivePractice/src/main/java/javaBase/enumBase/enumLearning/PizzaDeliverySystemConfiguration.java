package javaBase.enumBase.enumLearning;

import lombok.Getter;

/**
 * description: 单例模式
 * @author: JiaHao Wang
 * @date: 2021/1/6 10:25
 * @modified By:
 */
@Getter
public enum PizzaDeliverySystemConfiguration {

    /**
     * 实例对象
     */
    INSTANCE;

    /**
     * 交付Pizza的 策略对象
     */
    private final PizzaDeliveryStrategy deliveryStrategy = PizzaDeliveryStrategy.NORMAL;

    public static PizzaDeliverySystemConfiguration getInstance() {
        return INSTANCE;
    }

}
