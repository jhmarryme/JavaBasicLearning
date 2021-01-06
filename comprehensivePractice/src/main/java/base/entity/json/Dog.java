package base.entity.json;

import lombok.Data;

@Data
public class Dog extends Animal {
    private String petName;

    public Dog() {
        petName = "Milo";
        type = "Dog";
    }

}