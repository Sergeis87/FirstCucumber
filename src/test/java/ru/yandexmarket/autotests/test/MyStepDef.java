package ru.yandexmarket.autotests.test;

import com.codeborne.selenide.Selenide;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class MyStepDef {
    @Given("Открываем сайт {string}")
    public void открываемСайт(String arg0) {
        Selenide.open(arg0);
    }

    @When("Кликаем на все сервисы")
    public void кликаемНаВсеСервисы() {
        $(By.xpath("//a[@title = 'Все сервисы']")).click();
    }

    @And("Нажимаем на значок Маркет")
    public void нажимаемНаЗначокМаркет() {
        $(By.xpath("//div[text() = 'Маркет']")).click();
    }

    @And("Переходим в яндексМаркет")
    public void переходимВЯндексМаркет() {
        switchTo().window(1);
    }
}
