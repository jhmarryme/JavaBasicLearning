package reading.nowCoderAlgorithm.chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现一种狗猫队列的结构，要求如下： 用户可以调用add方法将cat类或dog类的 实例放入队列中；
 * 用户可以调用pollAll方法，将队列中所有的实例按照进队列 的先后顺序依次弹出；
 * 用户可以调用pollDog方法，将队列中dog类的实例按照 进队列的先后顺序依次弹出；
 * 用户可以调用pollCat方法，将队列中cat类的实 例按照进队列的先后顺序依次弹出；
 * 用户可以调用isEmpty方法，检查队列中是 否还有dog或cat的实例；
 * 用户可以调用isDogEmpty方法，检查队列中是否有dog 类的实例；
 * 用户可以调用isCatEmpty方法，检查队列中是否有cat类的实例。
 * @author jhmarryme.cn
 * @date 2019/7/31 15:43
 */
public class Code_04_CatDogQueue {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue{
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return this.pet;
        }

        public long getCount() {
            return this.count;
        }
        public String getEnterPetType(){
            return this.pet.getPetType();
        }
    }

    public static class CatDogQueue{
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public CatDogQueue() {
            dogQ = new LinkedList<>();
            catQ = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet){
            if (pet instanceof Cat){
                this.catQ.add(new PetEnterQueue(pet, this.count++));
            } else if (pet instanceof Dog){
                this.dogQ.add(new PetEnterQueue(pet, this.count++));
            } else {
                throw new RuntimeException("err, not dog or cat");
            }
        }

        public Pet pollAll(){
            if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()){
                if (this.dogQ.peek().getCount() > this.catQ.peek().getCount()){
                    return this.dogQ.poll().getPet();
                } else {
                    return this.catQ.poll().getPet();
                }
            } else if (!this.dogQ.isEmpty()){
                return this.dogQ.poll().getPet();
            } else if (!this.catQ.isEmpty()){
                return this.catQ.poll().getPet();
            } else {
                throw new RuntimeException("err, queue is empty!");
            }
        }
        public Dog pollDog() {
            if (!this.isDogQueueEmpty()) {
                return (Dog) this.dogQ.poll().getPet();
            } else {
                throw new RuntimeException("Dog queue is empty!");
            }
        }

        public Cat pollCat() {
            if (!this.isCatQueueEmpty()) {
                return (Cat) this.catQ.poll().getPet();
            } else{
                throw new RuntimeException("Cat queue is empty!");
            }
        }

        public boolean isEmpty() {
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        public boolean isDogQueueEmpty() {
            return this.dogQ.isEmpty();
        }

        public boolean isCatQueueEmpty() {
            return this.catQ.isEmpty();
        }
    }

    public static void main(String[] args) {
        CatDogQueue test = new CatDogQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogQueueEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }
}