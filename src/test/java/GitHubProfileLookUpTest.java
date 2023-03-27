import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class GitHubProfileLookUpTest {

    public static final String urlGithubProfile = "https://github.com/Henry-Darana";
    public static final String urlDrinkHaus = "https://drink.haus/";
    By lblNameLocators = By.xpath("//span[contains(text(), 'Henry-Darana')]");
    WebDriver driver;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions cOptions = new ChromeOptions();
        cOptions.addArguments("--remote-allow-origins=*");
        cOptions.addArguments("--headless");
        cOptions.addArguments("--disable-gpu");
        driver = new ChromeDriver(cOptions);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterSuite
    public void tearDown() {

        driver.close();
    }

    @Test(dataProvider = "hack-provider")
    public void visitGitHubProfile(int i) {

        driver.get(urlGithubProfile);
        int actual = driver.findElements(lblNameLocators).size();
        Assert.assertTrue(actual > 0);
    }

    @DataProvider(name = "hack-provider")
    public Object[][] hackProvider() {
        Object[][] counter = new Object[100][1];
        for (int i = 0; i < 100; i++) {
            counter[i][0] = i;
        }
        return counter;
    }
}
