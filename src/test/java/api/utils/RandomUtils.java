package api.utils;

import com.github.javafaker.Faker;
import java.util.concurrent.ThreadLocalRandom;
public class RandomUtils {
    Faker faker = new Faker();

    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    public String getRandomFirstName() {
        return faker.name().firstName();
    }
    public String getRandomUserName() {
        return faker.pokemon().name();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String getRandomPhoneNumber(int phoneNumberLength) {
        String phoneNumber = faker.number().digits(phoneNumberLength);
        return phoneNumber;
    }

    public int getRandomId() {
        int id = getRandomInt(1, 999);
        return id;
    }

}
