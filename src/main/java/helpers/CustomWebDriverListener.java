package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;

public class CustomWebDriverListener implements WebDriverListener {
    private final WebDriver driver;

    public CustomWebDriverListener(WebDriver currentDriver) {
        this.driver = currentDriver;
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        ScreenshotUtils.takeAndAddScreenshotToAllureReport(driver);
    }
}