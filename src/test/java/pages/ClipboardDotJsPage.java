package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClipboardDotJsPage {

    private WebElement copyToClipboardButton;
    private WebElement copyToClipboardField;
    private WebDriverWait wait;

    public ClipboardDotJsPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@id='example-target']//button[@class='btn']")));
        copyToClipboardButton = driver.findElement(By.xpath("//div[@id='example-target']//button[@class='btn']"));
        copyToClipboardField = driver.findElement(By.id("foo"));
    }

    public void clickOnCopyToClipboardButton(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].focus();", copyToClipboardButton);
        copyToClipboardButton.click();
    }

    public String getTextFromCopyToClipboardField(WebDriver driver) {
        return copyToClipboardField.getAttribute("value");
    }
}
