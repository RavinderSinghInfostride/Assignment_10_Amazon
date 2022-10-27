package Pages;

import Enums.ProductQuestionAndAnswer_Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductDescriptionPage {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> productSize;
    List<WebElement> productColour;

    By searchedProduct = By.xpath("//span[@id='productTitle']");
    String productSizeXpath = "//div[@id='variation_size_name']//ul//li";
    String productColourXpath = "//div[@id='variation_color_name']//ul//li";
    By productColourValueDisplayed = By.xpath("//div[@id='variation_color_name']//label[@class='a-form-label']//following::span[1]");
    By questionAndAnswerLink = By.xpath("//span[contains(text(),' 391 answered questions ')]");
    String questions = "//div[contains(@class,'askTeaserQuestions')]//div[@id='%s']";
    By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");
    By viewCartButton = By.xpath("//span[@id='attach-sidesheet-view-cart-button-announce']");

    public ProductDescriptionPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchedProductName() {
        String mobileName = driver.findElement(searchedProduct).getText();
        System.out.println(mobileName);
    }

    public void searchedProductSize() {
        productSize = driver.findElements(By.xpath(productSizeXpath));
        int numberOfProductSize = productSize.size();
        System.out.println("Product sizes are : ");
        for (int i = 0; i < numberOfProductSize; i++) {
            String productSizeValue = productSize.get(i).getText();
            System.out.println(productSizeValue);
        }
    }

    public void searchedProductColour() {
        productColour = driver.findElements(By.xpath(productColourXpath));
        int numberOfProductColour = productColour.size();
        System.out.println("Product colours are : ");
        for (int j = 0; j < numberOfProductColour; j++) {
            Actions action = new Actions(driver);
            action.moveToElement(productColour.get(j)).perform();
            String productColourValue = driver.findElement(productColourValueDisplayed).getText();
            System.out.println(productColourValue);
        }
    }

    public void questionAndAnswerList() throws InterruptedException {
        driver.findElement(questionAndAnswerLink).click();
        for (ProductQuestionAndAnswer_Enums c : ProductQuestionAndAnswer_Enums.values()) {
            Thread.sleep(2000);
            String questionValue = driver.findElement(By.xpath(String.format(questions, c.getResourcesName()))).getText();
            System.out.println(questionValue);
        }
    }

    public void addProductToCart() {
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton));
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(viewCartButton)).click().perform();
    }
}