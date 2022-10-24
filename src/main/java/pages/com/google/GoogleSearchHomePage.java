package pages.com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class GoogleSearchHomePage extends BasePage {
    private final WebElement searchField;

    public GoogleSearchHomePage(WebDriver currentDriver) {
        super(currentDriver);
        this.searchField = currentDriver.findElement(By.name("q"));
    }

    public void searchFor(String line) {
        searchField.sendKeys(line);
        searchField.submit();
    }
}