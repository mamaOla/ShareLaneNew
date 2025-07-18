import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {
    /*
    c. File Upload
- загрузить файл
- проверить, что имя файла на странице совпадает с именем
загруженного файла
     */
    @Test
    public void checkFileUpload() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/upload");
        try {
            // Путь к файлу для загрузки и загрузка
            File file = new File("src/test/java/resources/123.txt");
            driver.findElement(By.cssSelector("[type=file]")).sendKeys(file.getAbsolutePath());

            driver.findElement((By.id("file-submit"))).click();

            // Проверить, что имя файла отображается на странице
            WebElement uploadedFileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));
            String displayedFileName = uploadedFileName.getText();

            if (displayedFileName.equals("resources/123.txt")) {
                System.out.println("Имя файла совпадает");
            } else {
                System.out.println("Имя файла не совпадает");
            }
        } finally {
            driver.quit();
        }
    }
}
