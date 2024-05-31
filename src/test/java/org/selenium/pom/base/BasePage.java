package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waitLong;
    protected WebDriverWait waitShort;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitLong = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void load(String endPoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitForOverlaysToDisappear(List<WebElement> overlays) {
        if (!overlays.isEmpty()) {
            waitLong.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS ARE INVISIBLE");
        } else {
            System.out.println("OVERLAYS NOT FOUND");
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeClickableUsingBy(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForTextMatches(By element, String text) {
        return wait.until(ExpectedConditions.textToBe(element, text));
    }

    public void waitForTitleToContain(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    public Boolean waitForUrlToBe(String url) {
        return wait.until(ExpectedConditions.urlToBe(url));
    }

    public Boolean waitForUrlToContain(String partialUrl) {
        return wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    public boolean waitForElementToDisappear(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitForInvisibilityOfElement(By element) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForAttributeToBe(WebElement element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public boolean waitForAttributeToContain(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void waitForFrameToBeAvailableAndSwitchToIt(WebElement frameElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public void waitForStalenessOfElement(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> {
            assert driver1 != null;
            return ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        };
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(pageLoadCondition);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
            }
        }
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
