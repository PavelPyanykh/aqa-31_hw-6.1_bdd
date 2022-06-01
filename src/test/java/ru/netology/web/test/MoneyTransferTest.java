package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {
    LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

    @Test
    void shouldTransferFromFirstToSecond() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        int firstBalance = dashboardPage.getFirstCardBalance();
        int secondBalance = dashboardPage.getSecondCardBalance();
        var transferMoney = dashboardPage.secondCardButton();
        String sum = "7000";
        var card = DataHelper.getFirstCard();
        transferMoney.validTransfer(sum, card);
        assertEquals(secondBalance + Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
        assertEquals(firstBalance - Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
    }

    @Test
    void shouldTransferFromSecondToFirst() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        int firstBalance = dashboardPage.getFirstCardBalance();
        int secondBalance = dashboardPage.getSecondCardBalance();
        var transferMoney = dashboardPage.firstCardButton();
        String sum = "7000";
        var card = DataHelper.getSecondCard();
        transferMoney.validTransfer(sum, card);
        assertEquals(firstBalance + Integer.parseInt(sum), dashboardPage.getFirstCardBalance());
        assertEquals(secondBalance - Integer.parseInt(sum), dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferOverBalance() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        var transferMoney = dashboardPage.secondCardButton();
        String sum = "23000";
        var card = DataHelper.getFirstCard();
        transferMoney.validTransfer(sum, card);
        transferMoney.errorMessage();
    }
}
