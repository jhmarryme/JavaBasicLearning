package javaBase.enumBase.enumLearning;

/**
 * description: 策略模式
 * @author Jiahao Wang
 * @date 2021/1/6 10:40
 */
public enum PizzaDeliveryStrategy {

    /**  
     * 快速模式
     */
    EXPRESS {
        @Override
        public void deliver(Pizza pizza) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    /**  
     * 正常模式
     */
    NORMAL {
        @Override
        public void deliver(Pizza pizza) {
            System.out.println("Pizza will be delivered in normal mode");
        }
    }
    ;

    /**
     * 交付 Pizza
     * <br/>
     * @param pizza
     */
    public abstract void deliver(Pizza pizza);

}
