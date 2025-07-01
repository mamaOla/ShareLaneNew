import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortableDataTablesTest {
    @Test
    //Sortable Data Tables - Проверить содержимое нескольких (3-5) ячеек
    //таблицы. Использовать xpath типа //table//tr[1]//td[1] - получение первой
    //ячейки из первого ряда первой таблицы и так далее
    public void checkSortable() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/tables");

        // Массив XPath ячеек и ожидаемых значений
        String[][] cellsToCheck = {
                {"//tbody/tr[1]/td[1]", "Smith"},
                {"//tbody/tr[1]/td[2]", "John"},
                {"//tbody/tr[1]/td[3]", "jsmith@gmail.com"},
                {"//tbody/tr[1]/td[4]", "$500.00"}, // тут я заложил ошибку 500$ вместо 50$
                {"//tbody/tr[1]/td[5]", "http://www.jsmith.com"}
        };

        // Цикл перебирает все элементы массива cellsToCheck
        for (String[] cellInfo : cellsToCheck) {
            // Объявление переменных внутри цикла
            String xpath = cellInfo[0]; // извлекает XPath из текущего массива, чтобы найти нужную ячейку таблицы.
            String expectedValue = cellInfo[1]; // ожидаемое значение, с которым мы сравним содержимое ячейки.

            WebElement cell = driver.findElement(By.xpath(xpath)); // ищет на странице элемент по XPath, соответствующий текущей ячейке таблицы.
            String actualValue = cell.getText(); // извлекает видимый текст внутри ячейки

            // Вывод в консоль
            System.out.println("Проверка: " + xpath);
            System.out.println("Ожидаемое: " + expectedValue);
            System.out.println("Фактическое: " + actualValue);

            if (actualValue.equals(expectedValue)) {
                System.out.println("Результат: совпадает ✅");
            } else {
                String errorMsg = "Значение не совпадает в ячейке: " + xpath +
                        "\nОжидание: " + expectedValue +
                        "\nПолучено: " + actualValue;
                System.out.println("Результат: НЕ совпадает ❌");
                throw new AssertionError(errorMsg); // формирует сообщение об ошибке и выбрасывает исключение AssertionError, что завершит тест с ошибкой.
            }
            System.out.println("-------------------------");
        }
        driver.quit();
    }
}
