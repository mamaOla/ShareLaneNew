import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckboxesTest {
    @Test
    //Checkboxes - проверить, что первый чекбокс unchecked, отметить
    //первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
    //checked, сделать unheck, проверить, что он unchecked
    //Локатор: By.cssSelector("[type=checkbox]”)
    public void checkBoxes() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Локатор для чекбоксов
        By checkboxesLocator = By.cssSelector("[type=checkbox]");

        // Находим все чекбоксы
        List<WebElement> checkboxes = driver.findElements(checkboxesLocator);

        // Проверяем, что первый чекбокс изначально unchecked
        WebElement checkbox1 = checkboxes.get(0);
        if (checkbox1.isSelected()) {
            throw new AssertionError("Первый чекбокс должен быть unchecked");
        }
        System.out.println("Первый чекбокс изначально unchecked");

        // Отметить первый чекбокс
        checkbox1.click();
        if (!checkbox1.isSelected()) {
            throw new AssertionError("Первый чекбокс не checked после клика");
        }
        System.out.println("Первый чекбокс checked успешно");

        // Проверить, что второй чекбокс изначально checked
        WebElement checkbox2 = checkboxes.get(1);
        if (!checkbox2.isSelected()) {
            throw new AssertionError("Второй чекбокс должен быть checked");
        }
        System.out.println("Второй чекбокс изначально checked");

        // Сделать uncheck второго чекбокса
        checkbox2.click();
        if (checkbox2.isSelected()) {
            throw new AssertionError("Второй чекбокс не unchecked после клика");
        }
        System.out.println("Второй чекбокс unchecked успешно");
        driver.quit();
    }
}
