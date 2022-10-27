package Pages;

import Enums.CartPage_Enums;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By productAddedVerify = By.xpath("//span[@class='a-truncate-cut']");
    By quantityDropdown = By.xpath("(//div[@class='a-row sc-action-links']//span[@class='a-button-inner'])[1]");
    String productQuantityValue = "//a[@id='quantity_4']";
    By verifyProductQuantity = By.xpath("//span[@class='a-dropdown-prompt']");
    By totalOrderAmount = By.xpath("//div[contains(@class,'sc-subtotal-activecart')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyProductAddedToCart() {
        String verifyProductAdded = driver.findElement(productAddedVerify).getText();
        Assert.assertEquals("Apple iPhone 13 (128GB) - Blue", verifyProductAdded);
    }

    public void increaseProductQuantity() {
        driver.findElement(quantityDropdown).click();
        driver.findElement(By.xpath(String.format(productQuantityValue, CartPage_Enums.ProductQuantityFour.getResourcesName()))).click();
    }

    public void verifyQuantityOfProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyProductQuantity));
        String quantity = driver.findElement(verifyProductQuantity).getText();
        Assert.assertEquals("4", quantity);
    }

    public void listTotalOrderAmount() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalOrderAmount));
        Thread.sleep(2000);
        String totalAmountOfOrder = driver.findElement(totalOrderAmount).getText();
        System.out.println(totalAmountOfOrder);
        Assert.assertEquals("Subtotal (4 items):   2,67,600.00",totalAmountOfOrder);
    }
}