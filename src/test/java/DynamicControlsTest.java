import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicControlsTest {
    /* b. Dynamic Controls
- нажать на кнопку Remove около чекбокса
- дождаться надписи “It’s gone”
- проверить, что чекбокса нет
- найти инпут
- проверить, что он disabled
- нажать на кнопку
- дождаться надписи “It's enabled!”
- проверить, что инпут enabled
     */
    @Test
    public void checkDynamicControls() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Нажать кнопку "Remove" рядом с чекбоксом
            driver.findElement(By.xpath("//button[text()='Remove']")).click();

            // Дождаться текста "It's gone"
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "It's gone"));

            // Проверить, что чекбокс исчез
            boolean isCheckboxPresent = driver.findElements(By.xpath("//input[@type=checkbox]")).size() > 0;
            if (!isCheckboxPresent) {
                System.out.println("Чекбокс исчез");
            } else {
                System.out.println("Чекбокс всё ещё есть");
            }

            // Найти инпут
            WebElement input = driver.findElement(By.xpath("//input[@disabled]"));

            // Проверить, что он disabled
            if (!input.isEnabled()) {
                System.out.println("Инпут отключен");
            } else {
                System.out.println("Инпут активен");
            }

            // Нажать кнопку "Enable"
            driver.findElement(By.xpath("//button[text()='Enable']")).click();

            // Дождаться текста "It's enabled!"
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("message"), "It's enabled!"));

            // Проверить, что инпут включен
            if (input.isEnabled()) {
                System.out.println("Инпут активен");
            } else {
                System.out.println("Инпут всё ещё отключен");
            }
        } finally {
            driver.quit();
        }
    }
}
