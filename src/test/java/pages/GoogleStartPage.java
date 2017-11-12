package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleStartPage {

    private WebElement searchField;
    private WebDriverWait wait;

    public GoogleStartPage(WebDriver webDriver) {
        wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
        searchField = webDriver.findElement(By.id("lst-ib"));
    }

    public void insertInSearchFieldFromClipboard() {
        searchField.sendKeys(Keys.CONTROL + "v");
    }

    public void setFocusOnSearchField(WebDriver webDriver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("arguments[0].focus();", searchField);
    }

    public String getTextFromSearchField(WebDriver webDriver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        return (String) jsExecutor.executeScript("return arguments[0].value;", searchField);
    }
}