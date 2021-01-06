package javaBase.enumBase.enumLearning;

import lombok.*;

/**
 * description: 枚举的基本使用方法简单练习 - 枚举类型的属性,方法和构造函数
 *      1. 枚举的比较必须用==, 使用equals会出现问题
 * @author Jiahao Wang
 * @date 2020/8/12 8:59
 */
@Data
public class Pizza {
    private PizzaStatus status;

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public enum  PizzaStatus {
        /**
         * 预定
         */
        ORDERED (5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        /**
         * 就绪
         */
        READY (2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        /**
         * 已送达
         */
        DELIVERED (0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }
    }

    /**
     * 判断Pizza是否已经准备好
     * <br/>
     * @author Jiahao Wang
     * @date 2020/8/12 9:44
     * @return boolean
     */
    public boolean isDeliverable() {
        return this.status.isReady();
    }

    /**
     * 打印当前状态下, 提交需要的时间
     * <br/>
     * @author Jiahao Wang
     * @date 2021/1/6 10:51
     */
    public void printTimeToDeliver(){
        //
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }

    /**
     * 交付 Pizza
     * <br/>
     * @author Jiahao Wang
     * @date 2021/1/6 10:36
     */
    public void deliver() {
        if (isDeliverable()) {
            PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy().deliver(this);
            this.setStatus(PizzaStatus.DELIVERED);
        }
    }


    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        pizza.setStatus(PizzaStatus.READY);
        System.out.println("pizza.isDeliverable() = " + pizza.isDeliverable());
    }
}
