package chromwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;

/**
 * @Author candy-wind
 * @Data: 2020-07-12 13:29
 * @Version 1.0
 */
public class WebDriverTest {

    public static void main(String[] args) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        URL path = loader.getResource("chromedriver");
        System.setProperty("webdriver.chrome.driver",path.getPath());
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("-headless");
        WebDriver driver =new ChromeDriver(chromeOptions);
        driver.get("https://login.10086.cn/login.html?channelID=12003&backUrl=http%3A%2F%2Fwww.10086.cn%2Findex%2Fyn%2Findex_871_871.html");
        driver.close();

    }
}
