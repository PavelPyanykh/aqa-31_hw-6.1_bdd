package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        String cardNumber;
    }

    public static CardNumber getFirstCard() {
        return new CardNumber("5559 0000 0000 0001");
    }

    public static CardNumber getSecondCard() {
        return new CardNumber("5559 0000 0000 0002");
    }
}
