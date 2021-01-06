package base.entity.json;

import lombok.Data;

@Data
public class Cow extends Animal {
    private String breed;

    public Cow() {
        breed = "Jersey";
        type = "Cow";
    }

}