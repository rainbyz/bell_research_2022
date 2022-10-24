import helpers.CustomWebDriverListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.time.Duration;

public class BaseTests {
    protected WebDriver currentDriver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        // System.setProperty("webdriver.chrome.driver", System.getProperty("user.home") + "/Downloads/chromedriver");
        currentDriver = new ChromeDriver();
        currentDriver.manage().window().maximize();
        currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        currentDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        currentDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        WebDriverListener customWebDriverListener = new CustomWebDriverListener(currentDriver);
        EventFiringDecorator<WebDriver> eventFiringDecorator = new EventFiringDecorator<>(customWebDriverListener);
        currentDriver = eventFiringDecorator.decorate(currentDriver);
    }

    @AfterEach
    public void shutDown() {
        currentDriver.quit();
    }
}