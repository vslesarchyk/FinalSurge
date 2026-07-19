package dto;


import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class ShoesFactory {
    public static Shoes getShoes() {
        Faker faker = new Faker();
        return new Shoes(faker.commerce().productName(),
                faker.company().name(),
                faker.commerce().promotionCode(),
                faker.commerce().price(),
                new SimpleDateFormat("MM/dd/yyyy")
                        .format(faker.date().past(365, TimeUnit.DAYS)),
                String.valueOf(faker.number().numberBetween(35, 45)),
                String.valueOf(faker.number().numberBetween(0, 300)),
                "km",
                String.valueOf(faker.number().numberBetween(500, 1000)),
                "km",
                faker.lorem().sentence());
    }
}
