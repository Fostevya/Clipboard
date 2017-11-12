package tests;

import helpers.ClipboardHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ClipboardDotJsPage;
import pages.GoogleStartPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class StringInClipboardEqualsFieldText {

    private WebDriver driver;
    private WebDriverWait wait;
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private String clipboardDotJsUrl = "https://clipboardjs.com/";
    private String googleStartSearchUrl = "https://www.google.ru";
    private String someTextForTest = "This is text for insert from clipboard [to search field]";
    private ClipboardDotJsPage clipboardDotJsPage;
    private GoogleStartPage googleStartPage;
    private ClipboardHelper clipboardHelper;

    @BeforeTest
    public void beforeTest() {
        capabilities.setBrowserName("chrome");
        clipboardHelper = new ClipboardHelper();
        driver = new RemoteWebDriver(capabilities);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void stringInClipboardEqualsFieldText() {
        driver.get(clipboardDotJsUrl);
        clipboardDotJsPage = new ClipboardDotJsPage(driver);
        clipboardDotJsPage.clickOnCopyToClipboardButton(driver);
        try {
            assertEquals(clipboardHelper.getTextFromClipboard(), clipboardDotJsPage.getTextFromCopyToClipboardField(driver));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Clipboard is null");
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            fail("Active flavour not supports");
        }
        driver.get(googleStartSearchUrl);
        googleStartPage = new GoogleStartPage(driver);
        googleStartPage.setFocusOnSearchField(driver);
        clipboardHelper.insertTextInClipboard(someTextForTest);
        googleStartPage.insertInSearchFieldFromClipboard();
        try {
            assertEquals(clipboardHelper.getTextFromClipboard(), googleStartPage.getTextFromSearchField(driver));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Clipboard is null");
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
            fail("Active flavour not supports");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
