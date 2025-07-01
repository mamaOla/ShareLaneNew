import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class TyposTest {
    @Test
    //Typos - Проверить соответствие параграфа орфографии
    //Локатор: By.tagName(“p”)
    public void checkTypos() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/typos");

        // Находим первый параграф
        WebElement paragraph = driver.findElement(By.tagName("p"));

        // Получаем текст параграфа
        String paragraphText = paragraph.getText();
        System.out.println("Текст параграфа: " + paragraphText);

        // Ожидаемый текст (пример)
        String expectedText = "This example demonstrates a typo being introduced. It does it randomly on each page load.";

        // Проверка соответствия текста
        if (paragraphText.equals(expectedText)) {
            System.out.println("Текст соответствует ожидаемому.");
        } else {
            System.out.println("Текст НЕ соответствует ожидаемому.");
            System.out.println("Ожидалось: " + expectedText);
        }
        driver.quit();
    }
}
