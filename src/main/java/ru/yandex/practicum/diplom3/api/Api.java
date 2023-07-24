package ru.yandex.practicum.diplom3.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Api {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String PATH_REGISTER = "api/auth/register";
    private static final String PATH_LOGIN = "api/auth/login";
    private static final String PATH_DELETE = "api/auth/user";

    private static RequestSpecification getSpec() {

        RestAssured.filters(new ResponseLoggingFilter());

        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ValidatableResponse registerUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(PATH_REGISTER)
                .then();
    }

    public static ValidatableResponse loginUser(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH_LOGIN)
                .then();
    }

    public static void deleteUser(String token) {
        given()
                .spec(getSpec())
                .auth().oauth2(token)
                .delete(PATH_DELETE)
                .then();
    }
}