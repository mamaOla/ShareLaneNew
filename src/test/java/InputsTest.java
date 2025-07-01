import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class InputsTest {
    @Test
    //Inputs - Проверить на возможность ввести различные цифровые и
    //нецифровые значения, используя Keys.ARROW_UP И
    //Keys.ARROW_DOWN
    //Локатор: By.tagName(“input”)
    public void checkInputs() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/inputs");

        // Находим все input элементы
        WebElement input = driver.findElement(By.tagName("input"));

        // Вводим числовое значение
        input.clear();
        input.sendKeys("50");
        System.out.println("Введено число: 50");

        // Используем стрелку вверх
        input.sendKeys(Keys.ARROW_UP);
        System.out.println("Нажата клавиша ARROW_UP");
        String value1 = input.getAttribute("value");
        System.out.println("Текущее значение в input: " + value1);

        // Используем стрелку вниз 2 раза
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ARROW_DOWN);
        System.out.println("Нажата клавиша ARROW_DOWN 2 раза");
        String value2 = input.getAttribute("value");
        System.out.println("Текущее значение в input: " + value2);

        // Вводим нечисловое значение
        input.clear();
        input.sendKeys("TestString");
        System.out.println("Введена строка: TestString");

        // Проверка, что значение осталось
        String currentValue = input.getAttribute("value");
        System.out.println("Текущее значение в input: " + currentValue);
        driver.quit();
    }
}
