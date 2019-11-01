package designPatternLesson.decorator;

/**
 * 被装饰之后的猪, 跑得快
 * @author jhmarryme.cn
 * @date 2019/10/14 11:36
 */
public class FastPig extends DecoratorPig {
    public FastPig(Animal pig) {
        super(pig);
    }

    @Override
    public void run() {
        super.run();
        System.out.println(".....小猪跑得飞快");
    }
}
