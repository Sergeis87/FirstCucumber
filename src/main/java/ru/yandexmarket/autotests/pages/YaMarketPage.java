package ru.yandexmarket.autotests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.fail;

/**
 * Открытие каталога и переход в раздел электроника
 */
public class YaMarketPage {
    @Step("Перейти в Каталог -> Навести курсор на раздел  Электроника")
    public YaMarketPage findElectronic() {
        $(By.xpath("//button[@id='catalogPopupButton']")).click();
        refresh();
        $(By.xpath("//button[@id='catalogPopupButton']")).click();
        $(By.xpath("(//span[text()='Электроника'])[2]")).hover();
        $(By.xpath("//a[text()='Смартфоны']")).click();
        return this;
    }

    /**
     * Осуществление поиска в фильтрах по заданному производителю
     */
    @Step("Задать параметр «Производитель» {manufacturer}")
    public YaMarketPage findProduct(String manufacturer) {
        $(By.xpath("//span[text()='Показать всё']")).click();
        $(By.xpath("//label[text()='Найти производителя']/../input")).setValue(manufacturer);
        $(By.xpath("//div[@data-baobab-name='FilterValue']//span[text()='" + manufacturer + "']/../..")).click();
        SelenideElement loader = $(By.xpath("//span[@aria-label='Загрузка...']"));
        loader.shouldBe(Condition.visible);
        $(By.xpath("//div[@data-zone-name='SearchPager']")).scrollTo();
        loader.shouldNotBe(Condition.visible, Duration.ofSeconds(120));
        return this;
    }

    /**
     * Проверка отображения на всех страницах заданного производителя
     */
    @Step("Проверить что на странице производитель {production}")
    public YaMarketPage checkProductTitle(String title) {
        String lowerCaseTitle = title.toLowerCase();
        do {
            $(By.xpath("//a[text()='Пользовательское соглашение']")).scrollTo();
            ElementsCollection trHeaders = $$(By.xpath("//article[@data-autotest-id='product-snippet']//h3/a"));
            System.out.println("Количество товаров: " + trHeaders.size());
            for (SelenideElement as : trHeaders) {
                String actualLoweCaseTitle = as.getText().toLowerCase();
                if (!actualLoweCaseTitle.contains(lowerCaseTitle)) {
                    fail("Элемент " + actualLoweCaseTitle + " не содержит искомого значения: " + lowerCaseTitle);
                }
            }

            if ($(By.xpath("//span[text()='Вперёд']")).exists()) {
                $(By.xpath("//span[text()='Вперёд']")).scrollTo();
                $(By.xpath("//span[text()='Вперёд']")).click();
                $(By.xpath("//span[@aria-label='Загрузка...']")).shouldBe(Condition.visible);
                $(By.xpath("//div[@data-zone-name='SearchPager']")).scrollTo();
                $(By.xpath("//span[@aria-label='Загрузка...']")).shouldNotBe(Condition.visible,
                        Duration.ofSeconds(120));
            } else {
                break;
            }
        } while (true);

        return this;
    }

}
