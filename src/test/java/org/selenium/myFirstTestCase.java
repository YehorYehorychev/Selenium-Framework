package org.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class myFirstTestCase {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#menu-item-1227 > a")).click();
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(), "Search results: “Blue”");
        driver.findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        driver.findElement(By.cssSelector("a[title='View cart']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("td[class='product-name'] a")).getText(), "Blue Shoes");
        driver.findElement(By.cssSelector(".checkout-button")).click();
        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("demo");
        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("test");
        driver.findElement(By.cssSelector("#billing_company")).sendKeys("google");
        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.cssSelector("#billing_city")).sendKeys("San Francisco");
        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("94040");
        driver.findElement(By.cssSelector("#billing_email")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("#place_order")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector(".woocommerce-notice")).getText(), "Thank you. Your order has been received.");
        driver.close();
        driver.quit();
    }
}
