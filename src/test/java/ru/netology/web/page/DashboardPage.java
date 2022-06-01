package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final SelenideElement firstCardButton = $$("[data-test-id='action-deposit']").first();
    private final SelenideElement secondCardButton = $$("[data-test-id='action-deposit']").last();
    private final SelenideElement firstCard = $$(".list__item").first();
    private final SelenideElement secondCard = $$(".list__item").last();
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
        SelenideElement heading = $("[data-test-id=dashboard]");
        heading.shouldBe(Condition.visible);
    }

    public TransferMoney firstCardButton() {
        firstCardButton.click();
        return new TransferMoney();
    }

    public TransferMoney secondCardButton() {
        secondCardButton.click();
        return new TransferMoney();
    }

    public int getFirstCardBalance() {
        val text = firstCard.text();
        return extractFirstCardBalance(text);
    }

    private int extractFirstCardBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getSecondCardBalance() {
        val text = secondCard.text();
        return extractSecondCardBalance(text);
    }

    private int extractSecondCardBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
