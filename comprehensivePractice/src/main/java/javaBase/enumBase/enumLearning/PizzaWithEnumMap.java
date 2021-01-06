package javaBase.enumBase.enumLearning;

import java.util.*;

/**
 * description: EnumMap 简单应用
 *
 * @Author: Wjh
 * @Date: 2020/8/12 11:34
 * @Modified By:
 */
public class PizzaWithEnumMap {


    public static void main(String[] args) {

        List<Pizza> pzList = new ArrayList<>();
        Pizza pz1 = new Pizza();
        pz1.setStatus(Pizza.PizzaStatus.DELIVERED);

        Pizza pz2 = new Pizza();
        pz2.setStatus(Pizza.PizzaStatus.ORDERED);


        Pizza pz3 = new Pizza();
        pz3.setStatus(Pizza.PizzaStatus.ORDERED);

        Pizza pz4 = new Pizza();
        pz4.setStatus(Pizza.PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);
        EnumMap<Pizza.PizzaStatus, List<Pizza>> map = groupByPizzaStatus(pzList);

        System.out.println("map.get(Pizza.PizzaStatus.DELIVERED).size() = " + map.get(Pizza.PizzaStatus.DELIVERED).size());
        System.out.println("map.get(Pizza.PizzaStatus.ORDERED).size() = " + map.get(Pizza.PizzaStatus.ORDERED).size());
        System.out.println("map.get(Pizza.PizzaStatus.READY).size() = " + map.get(Pizza.PizzaStatus.READY).size());

    }

    /**
     * 根据不同的状态 将披萨分类存入map
     * <br/>
     * @author Jiahao Wang
     * @date 2020/8/13 9:50
     * @param pzList
     * @return java.util.EnumMap<javaBase.enumBase.enumLearning.Pizza.PizzaStatus, java.util.List < javaBase.enumBase.enumLearning.Pizza>>
     */
    private static EnumMap<Pizza.PizzaStatus, List<Pizza>> groupByPizzaStatus(List<Pizza> pzList) {
        EnumMap<Pizza.PizzaStatus, List<Pizza>> map = new EnumMap<>(Pizza.PizzaStatus.class);

        pzList.forEach(pizza -> {
            Pizza.PizzaStatus status = pizza.getStatus();
            if (map.containsKey(status)){
                map.get(status).add(pizza);
            } else {
                ArrayList<Pizza> pizzas = new ArrayList<>();
                pizzas.add(pizza);
                map.put(pizza.getStatus(), pizzas);
            }
        });

        return map;
    }


}
