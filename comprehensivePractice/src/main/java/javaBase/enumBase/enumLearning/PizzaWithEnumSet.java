package javaBase.enumBase.enumLearning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: EnumSet简单应用
 *
 * @Author: Wjh
 * @Date: 2020/8/12 10:10
 * @Modified By:
 */
@Data
public class PizzaWithEnumSet {

    /** 处于未提交的披萨状态 **/
    private static EnumSet<PizzaStatusWithEnumSet> undeliveredPizzaStatus =
            EnumSet.of(PizzaStatusWithEnumSet.ORDERED, PizzaStatusWithEnumSet.READY);

    private PizzaStatusWithEnumSet status;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public enum PizzaStatusWithEnumSet {
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

    public boolean isDeliverable(){
        return this.status.isReady();
    }

    /*
     * description: 返回所有处于未提交的披萨状态的pizza
     * @Param: [pizzaWithEnumSetList]
     * @Return: java.util.List<javaBase.enumBase.enumLearning.PizzaWithEnumSet>
     * @Author: Wjh
     * @Date: 2020/8/12 10:19
     * @Throws
     */
    public static List<PizzaWithEnumSet> getAllUndeliveredPizzas(List<PizzaWithEnumSet> pizzaWithEnumSetList) {
        return pizzaWithEnumSetList
                .stream()
                .filter(s -> undeliveredPizzaStatus.contains(s.getStatus()))
                .collect(Collectors.toList());
    }

    public void deliver() {
        if (isDeliverable()) {
            // deliver

            this.setStatus(PizzaStatusWithEnumSet.DELIVERED);
        }
    }

    public static void main(String[] args) {
        List<PizzaWithEnumSet> pzList = new ArrayList<>();
        PizzaWithEnumSet pz1 = new PizzaWithEnumSet();
        pz1.setStatus(PizzaWithEnumSet.PizzaStatusWithEnumSet.DELIVERED);

        PizzaWithEnumSet pz2 = new PizzaWithEnumSet();
        pz2.setStatus(PizzaWithEnumSet.PizzaStatusWithEnumSet.ORDERED);

        PizzaWithEnumSet pz3 = new PizzaWithEnumSet();
        pz3.setStatus(PizzaWithEnumSet.PizzaStatusWithEnumSet.ORDERED);

        PizzaWithEnumSet pz4 = new PizzaWithEnumSet();
        pz4.setStatus(PizzaWithEnumSet.PizzaStatusWithEnumSet.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        List<PizzaWithEnumSet> allUndeliveredPizzas = getAllUndeliveredPizzas(pzList);
        System.out.println("allUndeliveredPizzas.size() = " + allUndeliveredPizzas.size());
    }
}
