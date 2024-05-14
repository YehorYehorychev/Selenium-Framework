package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waitLong;
    protected WebDriverWait waitShort;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void load(String endPoint) {
        driver.get("https://askomdch.com" + endPoint);
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        if (!overlays.isEmpty()) {
            waitLong.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS ARE INVISIBLE");
        } else {
            System.out.println("OVERLAYS NOT FOUND");
        }
    }

    public WebElement waitForElementToBeVisible(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement waitForElementToBeClickable(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBePresent(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public boolean waitForTextToBePresentInElement(By element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
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

    public boolean waitForElementToDisappear(By element) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForAttributeToBe(By element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public boolean waitForAttributeToContain(By element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void waitForFrameToBeAvailableAndSwitchToIt(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
}
