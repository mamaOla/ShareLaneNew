import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenuTest {
    /*В проект HerokuApp добавить тесты для следующих страниц:
    a. Context Menu
    - правый клик по элементу
    - валидация текста на алерте
    - закрытие алерта
     */
    @Test
    public void checkContextMenu() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/context_menu");

        // Правый клик по элементу
        WebElement element = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).doubleClick().perform();

        // Валидация текста алерта
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        if (alertText.contains("You selected a context menu")) {
            System.out.println("Текст алерта подтверждён");
        } else {
            System.out.println("Текст алерта не совпадает");
        }

        // Закрытие алерта
        driver.switchTo().alert().accept();

        driver.quit();
    }
}
