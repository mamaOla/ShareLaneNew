import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {
    @Test
    //Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
    //Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
    //он выбран
    //Локатор: By.id(“dropdown”)
    public void checkDropdown() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/dropdown");

        // Локатор дропдауна
        By dropdownLocator = By.id("dropdown");
        WebElement dropdownElement = driver.findElement(dropdownLocator);

        // Создаем объект Select
        Select dropdown = new Select(dropdownElement);

        // Получаем все пункты
        List<WebElement> option = dropdown.getOptions();

        // Проверяем наличие опций
        System.out.println("Общее количество опций: " + option.size());

        // Создаем список только активных (enabled) опций
        List<WebElement> enabledOptions = option.stream() // Преобразует список option в поток (Stream<WebElement>). Это позволяет применять цепочку методов для обработки элементов.
                .filter(WebElement::isEnabled) // Фильтрует поток, оставляя только те элементы, у которых метод isEnabled() возвращает true
                .toList(); // Собирает все отфильтрованные элементы обратно в список (List<WebElement>).

        System.out.println("Активных опций: " + enabledOptions.size());

        // Выбираем первую активную
        String firstOptionText = enabledOptions.get(0).getText(); // Получаем текст первой активной опции из списка enabledOptions
        dropdown.selectByVisibleText(firstOptionText); // Выбираем в дропдауне опцию по видимому тексту, который мы только что получили.
        String selectedText = dropdown.getFirstSelectedOption().getText(); // Получаем текст текущей выбранной опции в дропдауне.
        System.out.println("Выбрана первая активная опция: " + selectedText);
        if (!selectedText.equals(firstOptionText)) {
            throw new AssertionError("Первая активная опция не выбрана"); // Проверка — совпадает ли текущий выбранный текст с ожидаемым
        }

        // Выбираем вторую активную
        String secondOptionText = enabledOptions.get(1).getText();
        dropdown.selectByVisibleText(secondOptionText);
        String selectedText2 = dropdown.getFirstSelectedOption().getText();
        System.out.println("Выбрана вторая активная опция: " + selectedText2);
        if (!selectedText2.equals(secondOptionText)) {
            throw new AssertionError("Вторая активная опция не выбрана");
        }
        driver.quit();
    }
}
