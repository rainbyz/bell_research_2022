package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    protected BasePage(WebDriver currentDriver) {
        this.driver = currentDriver;
    }
}