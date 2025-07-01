import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AddRemoveTest {

    @Test
    // 1. Add/Remove Elements - добавить 2 элемента, удалить элемент,
    //проверить количество элементов DELETE
    //Локаторы xpath:
    //a. By.xpath("//button[text()='Add Element']")
    //b. By.xpath("//button[text()='Delete']")
    public void checkAddRemove() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //Локаторы
        By addButtonLocator = By.xpath("//button[text()='Add Element']");
        By deleteButtonLocator = By.xpath("//button[text()='Delete']");

        // Добавляем два элемента
        for (int i = 0; i < 2; i++) {
            WebElement addButton = driver.findElement(addButtonLocator);
            addButton.click();
        }
        // Проверяем, что добавлено 2 кнопки "Delete"
        List<WebElement> deleteButtons = driver.findElements(deleteButtonLocator);
        System.out.println("Количество кнопок Delete после добавления: " + deleteButtons.size());
        if (deleteButtons.size() != 2) {
            throw new AssertionError("Должно быть 2 кнопки Delete");
            // выбрасывается ошибка, если условие не выполнено (например, не хватает кнопок)
        }
        // Удаляем один элемент
        deleteButtons.get(0).click();

        // Проверяем, что осталось 1 кнопка "Delete"
        List<WebElement> deleteButtonsAfter = driver.findElements(deleteButtonLocator);
        System.out.println("Количество кнопок Delete после удаления: " + deleteButtonsAfter.size());
        if (deleteButtonsAfter.size() != 1) {
            throw new AssertionError("Должна остаться 1 кнопка Delete");
        }
        driver.quit();
    }
}
