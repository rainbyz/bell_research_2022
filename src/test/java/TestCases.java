import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import pages.com.google.GoogleSearchHomePage;
import pages.com.google.GoogleSearchResultPage;
import pages.ru.open.OpenBankHomePage;

import java.util.List;

public class TestCases extends BaseTests {
    @DisplayName("Задание 1.2")
    @ParameterizedTest
    @CsvSource({"Открытие, Банк Открытие: Частным клиентам, USD, EUR"})
    public void testCase2(String keyWord, String title, String currency1, String currency2) {
        currentDriver.get("https://www.google.com/");
        GoogleSearchHomePage googleSearchHomePage = new GoogleSearchHomePage(currentDriver);
        googleSearchHomePage.searchFor(keyWord);
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(currentDriver);
        boolean flag1 = false;
        for (WebElement webElement : googleSearchResultPage.getResultTitlesWithLinks()) {
            if (webElement.getText().contains(title)) {
                flag1 = true;
                webElement.click();
                break;
            }
        }
        Assertions.assertTrue(flag1,
                "В результатах поиска на 1 странице в полученной выборке нет следующего заголовка: " + title);

        OpenBankHomePage openBankHomePage = new OpenBankHomePage(currentDriver);
        WebElement table = openBankHomePage.getCurrencyTable();
        int buyTextIndex = 0, sellTextIndex = 0;
        boolean flag2 = false;
        for (WebElement row : openBankHomePage.getRowsOfCurrencyTable(table)) {
            List<WebElement> columns = openBankHomePage.getColumnsOfCurrencyTable(row);
            for (int i = 0; i < columns.size(); i++) {
                String temp = columns.get(i).getText();
                if (temp.contains(openBankHomePage.getBuyText())) buyTextIndex = i;
                else if (temp.contains(openBankHomePage.getSellText())) sellTextIndex = i;
                else if (temp.equals(currency1) || temp.equals(currency2)) {
                    if (Double.parseDouble(columns.get(buyTextIndex).getText().replaceAll(",", "."))
                            < Double.parseDouble(columns.get(sellTextIndex).getText().replaceAll(",", "."))) {
                        flag2 = true;
                    }
                }
            }
        }
        Assertions.assertTrue(flag2,
                "Курс продажи продажи меньше курса покупки для " + currency1 + " или " + currency2);
    }
}