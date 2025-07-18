import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class FileDownloadTest {
    /*
    e. File Download (с зорачкай)
- Скачать файл
- Проверить наличие файла на файловой системе”
     */
    WebDriver driver;

    @Test
    public void checkFileDownload() throws InterruptedException {
        //Создается карта настроек chromePrefs для хранения предпочтений браузера
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        //Установка папки для автоматической загрузки:
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);//предпочтения передаются браузеру
        //Запускается Chrome с указанными настройками.
        driver = new ChromeDriver(options);

        driver.get("https://the-internet.herokuapp.com/download");

        //Поиск ссылок на скачивание:
        List<WebElement> links = driver.findElements(By.tagName("a"));

        //Поиск первой ссылки с расширением .txt
        String txtLink = null;
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && href.endsWith(".txt")) {
                txtLink = href;
                break;
            }
        }

        //Переход по ссылке для скачивания файла
        if (txtLink != null) {
            driver.get(txtLink);
        }

        // Пауза для скачивания
        Thread.sleep(5000);
        //Проверка наличия файла
        File dir = new File(System.getProperty("user.dir"));// текущая директория
        File[] files = dir.listFiles(); // список файлов в ней

        boolean fileFound = false;
        if (files != null) {
            for (File f : files) {
                if (f.getName().endsWith(".txt")) {
                    fileFound = true;
                    System.out.println("Найден файл: " + f.getName());
                    break;
                }
            }
        }
        if (fileFound) {
            System.out.println("Файл успешно скачан");
        } else {
            System.out.println("Файл не найден");
        }
    }
}

