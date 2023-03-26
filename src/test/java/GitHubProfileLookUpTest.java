import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class GitHubProfileLookUpTest {

    public static final String urlGithubProfile = "https://github.com/Henry-Darana";
    By lblNameLocators = By.xpath("//span[contains(text(), 'Henry-Darana')]");

    @Test
    public void visitGitHubProfile() {

        WebDriverManager
                .chromedriver()
                .setup();

        ChromeOptions cOptions = new ChromeOptions();
        cOptions.addArguments("--remote-allow-origins=*");
        cOptions.addArguments("--headless");
        cOptions.addArguments("--disable-gpu");
        WebDriver driver = new ChromeDriver(cOptions);

        driver
                .manage()
                .window()
                .maximize();

        driver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(urlGithubProfile);

        int actual = driver
                .findElements(lblNameLocators)
                .size();

        Assert.assertTrue(actual > 0);

        driver.close();

    }
}
