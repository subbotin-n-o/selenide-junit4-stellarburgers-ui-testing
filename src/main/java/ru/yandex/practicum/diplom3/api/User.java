package ru.yandex.practicum.diplom3.api;

import com.github.javafaker.Faker;
import lombok.*;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public String getNotValidPassword() {
        final Faker faker = new Faker(new Locale("en"));
        return faker.internet().password(4, 5);
    }

}