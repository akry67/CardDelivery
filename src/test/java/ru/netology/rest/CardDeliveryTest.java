package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {
    WebDriver driver;


    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldSubmitRequest() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1600x900";
        open("http://localhost:9999/");
        $x("//input[@placeholder=\"Город\"]").setValue("Самара");
        $x("//input[@placeholder=\"Дата встречи\"]").val("02.03.2022");
        $(By.name("name")).val("Кузнецова-Макалова Анна");
        $(By.name("phone")).val("+79270000000");
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));


    }

}
