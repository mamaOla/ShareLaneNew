import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {
    /*
    d. Frames
- Открыть iFrame
- Проверить, что текст внутри параграфа равен “Your content
goes here.”
     */
    @Test
    public void checkFrames() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get("https://the-internet.herokuapp.com/frames");

        try {
            driver.findElement(By.xpath("//a[@href='/iframe']")).click();
            // Переключение на iframe по его идентификатор или другому локатор
            driver.switchTo().frame("mce_0_ifr");

            // Найти параграф внутри iframe
            WebElement paragraph = driver.findElement(By.tagName("p"));
            String paragraphText = paragraph.getText();

            // Проверка текста
            if ("Your content goes here.".equals(paragraphText)) {
                System.out.println("Текст совпадает");
            } else {
                System.out.println("Текст не совпадает");
            }

            // Возврат к основному документу
            driver.switchTo().defaultContent();
        } finally {
            driver.quit();
        }
    }
}
