import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationMessageTest {
    @Test
    //Notification Messages - кликнуть на кнопку, дождаться появления
    //нотификации, проверить соответствие текста ожиданиям
    public void checkNotification() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        // Создается объект wait, который будет ждать до 10 секунд, пока не выполнится условие
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Кликаем по кнопке (замените локатор)
        WebElement button = driver.findElement(By.xpath(".//div/p/a")); // пример по ID
        button.click();

        // 2. Ждем появления уведомления
        WebElement notification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("flash")) // условие, которое возвращает элемент, когда он появляется и виден.
        );

        // 3. Проверяем текст уведомления
        String actualText = notification.getText();
        String expectedText = "Action successful\n×";

        if (actualText.equals(expectedText)) {
            System.out.println("Текст уведомления совпадает: " + actualText);
        } else {
            System.out.println("Текст уведомления не совпадает");
            System.out.println("Ожидали: " + expectedText);
            System.out.println("Получили: " + actualText);
        }
        driver.quit();
    }
}
