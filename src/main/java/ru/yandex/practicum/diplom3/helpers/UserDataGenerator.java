package ru.yandex.practicum.diplom3.helpers;

import com.github.javafaker.Faker;
import ru.yandex.practicum.diplom3.api.User;

import java.util.Locale;

public class UserDataGenerator {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final User user = new User();

    public static User getUser() {
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6, 10));
        user.setName(faker.name().firstName());

        return user;
    }

}