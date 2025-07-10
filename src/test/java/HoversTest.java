import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HoversTest {
    @Test
    //Hovers - Сделать цепочку из действий: наведение на профиль,
    //проверка имени, клик по ссылке, проверка что нет 404 ошибки. Повторить
    //для каждого из профилей. Использовать класс Actions и
    //https://stackoverflow.com/questions/17293914/how-to-perform-mouseover-function-in-selenium-webdriver-using-java
    public void checkIHovers123() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // implicitlyWait — устанавливает время, в течение которого WebDriver будет ждать появления элемента перед тем, как выбросить ошибку.
        // Duration.ofSeconds(20) — задает интервал ожидания в 20 секунд.

        driver.get("https://the-internet.herokuapp.com/hovers");

        Actions actions = new Actions(driver);

        // Получаем все профили на странице
        List<WebElement> profiles = driver.findElements(By.cssSelector(".figure"));

        for (int i = 0; profiles.size() > i; i++) {
            // profiles — это список (List<WebElement>) элементов, который ранее получили
            //get(i) — обращение к элементу с индексом i в этом списке.
            //profile — создается переменная типа WebElement, которая ссылается на текущий элемент из списка.
            WebElement profile = profiles.get(i);

            // Навести на профиль
            actions.moveToElement(profile).perform();

            // Проверить имя внутри профиля
            WebElement nameElement = profile.findElement(By.xpath(".//h5"));
            String name = nameElement.getText();
            System.out.println("Профиль " + (i + 1) + ": " + name);
            // Cравнить с ожидаемым именем
            Assert.assertEquals("name: user" + (i + 1), name);

            // Клик по ссылке внутри профиля
            WebElement link = profile.findElement(By.xpath(".//div/a"));
            actions.moveToElement(link).click().perform();

            // Проверка, что страница не 404
            // Selenium не умеет читать HTTP-статус напрямую, он управляет браузером, который показывает страницу.
            // Поэтому проверка URL, заголовка или содержимого — это наиболее надежные способы.
            String currentUrl = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();
            String pageContent = driver.getPageSource();

            if (currentUrl.contains("404") || currentUrl.toLowerCase().contains("notfound")) {
                System.out.println("Обнаружена страница с ошибкой 404 по URL");
            } else if (pageTitle.contains("404") || pageTitle.toLowerCase().contains("not found")) {
                System.out.println("Обнаружена страница с ошибкой 404 по заголовку");

            } else if (pageContent.contains("Not Found")) {
                System.out.println("Обнаружена страница с ошибкой 404 по содержимому");
            } else {
                System.out.println("Страница успешно загружена");
            }

            // Возврат на страницу профилей
            driver.navigate().back();
        }
        driver.quit();
    }
}
