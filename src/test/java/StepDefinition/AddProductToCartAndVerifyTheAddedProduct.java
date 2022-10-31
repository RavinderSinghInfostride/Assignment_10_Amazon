package StepDefinition;

import Enums.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddProductToCartAndVerifyTheAddedProduct extends BaseClass {
    List<WebElement> productSize;
    List<WebElement> productColour;
    String searchBar = "//div[@class='%s']//input[contains(@class,'nav-progressive-attribute')]";
    String productToAddToCart = "(//span[contains(text(),'%s')])[1]";
    By searchedProduct = By.xpath("//span[@id='productTitle']");
    String productSizeXpath = "//div[@id='%s']//ul//li";
    String productColourXpath = "//div[@id='variation_color_name']//ul//li";
    By productColourValueDisplayed = By.xpath("//div[@id='variation_color_name']//label[@class='a-form-label']//following::span[1]");
    By questionAndAnswerLink = By.xpath("//span[contains(text(),' 391 answered questions ')]");
    String questions = "//div[contains(@class,'askTeaserQuestions')]//div[@id='%s']";
    String answers = "//div[@id='%s']//following::div[contains(@class,'a-spacing-base')][1]";
    By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");
    By viewCartButton = By.xpath("//span[@id='attach-sidesheet-view-cart-button-announce']");
    By productAddedVerify = By.xpath("//span[@class='a-truncate-cut']");
    By quantityDropdown = By.xpath("(//div[@class='a-row sc-action-links']//span[@class='a-button-inner'])[1]");
    String productQuantityValue = "//a[@id='%s']";
    By verifyProductQuantity = By.xpath("//span[@class='a-dropdown-prompt']");
    By totalOrderAmount = By.xpath("//div[contains(@class,'sc-subtotal-activecart')]");

    @Given("user is on the home page of amazon")
    public void user_is_on_the_home_page_of_amazon() throws IOException {
        setup();
    }

    @And("user searches for a mobile")
    public void user_searches_for_a_mobile(DataTable productToSearch) {
        List<List<String>> data = Collections.singletonList(productToSearch.values());
        driver.findElement(By.xpath(String.format(searchBar, HomePage_Enums.SearchBarInputBox.getResourcesName()))).sendKeys(data.get(0).get(0));
        driver.findElement(By.xpath(String.format(searchBar, HomePage_Enums.SearchButton.getResourcesName()))).click();
    }

    @And("user selects the mobile and the mobile name is displayed on the console")
    public void user_selects_the_mobile_and_the_mobile_name_is_displayed_on_the_console() {
        driver.findElement(By.xpath(String.format(productToAddToCart, SearchProductDisplayPage_Enums.ProductName.getResourcesName()))).click();
        String mainWindow = driver.getWindowHandle();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(String.valueOf(childTabWindow.get(1)));
        String mobileName = driver.findElement(searchedProduct).getText();
        System.out.println(mobileName);
    }

    @And("the size and colour variations available for mobile are displayed on console")
    public void the_size_and_colour_variations_available_for_mobile_are_displayed_on_console() {
        productSize = driver.findElements(By.xpath(String.format(productSizeXpath, ProductDescriptionPage_Enums.ProductSizeVariation_Id.getResourcesName())));
        int numberOfProductSize = productSize.size();
        System.out.println("Product sizes are : ");
        for (int i = 0; i < numberOfProductSize; i++) {
            String productSizeValue = productSize.get(i).getText();
            System.out.println(productSizeValue);
        }
        productColour = driver.findElements(By.xpath(String.format(productColourXpath, ProductDescriptionPage_Enums.ProductColourVariation_Id.getResourcesName())));
        int numberOfProductColour = productColour.size();
        System.out.println("Product colours are : ");
        for (int j = 0; j < numberOfProductColour; j++) {
            Actions action = new Actions(driver);
            action.moveToElement(productColour.get(j)).perform();
            String productColourValue = driver.findElement(productColourValueDisplayed).getText();
            System.out.println(productColourValue);
        }
    }

    @And("user navigates to question and answer page and top three answers are listed")
    public void user_navigates_to_question_and_answer_page_and_top_three_answers_are_listed() {
        driver.findElement(questionAndAnswerLink).click();
        for (ProductQuestionAndAnswer_Enums c : ProductQuestionAndAnswer_Enums.values()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(questions, c.getResourcesName()))));
            WebElement questionXpath = driver.findElement(By.xpath(String.format(questions, c.getResourcesName())));
            WebElement answerXpath = driver.findElement(By.xpath(String.format(answers, c.getResourcesName())));
            String questionValue = questionXpath.getText();
            System.out.println(questionValue);
            String answerValue = answerXpath.getText();
            System.out.println(answerValue);
        }
    }

    @And("user adds product to cart")
    public void user_adds_product_to_cart() {
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton));
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(viewCartButton)).click().perform();
    }

    @And("the product added to cart is correct")
    public void the_product_added_to_cart_is_correct() {
        String verifyProductAdded = driver.findElement(productAddedVerify).getText();
        Assert.assertEquals("Apple iPhone 13 (128GB) - Blue", verifyProductAdded);
    }

    @When("user increases product quantity to four")
    public void userIncreasesProductQuantityToFour() {
        driver.findElement(quantityDropdown).click();
        driver.findElement(By.xpath(String.format(productQuantityValue, CartPage_Enums.ProductQuantityFour.getResourcesName()))).click();
    }

    @Then("verify product quantity is also increased to four")
    public void verifyProductQuantityIsAlsoIncreasedToFour() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyProductQuantity));
        String quantity = driver.findElement(verifyProductQuantity).getText();
        Assert.assertEquals("4", quantity);
    }

    @And("total amount of order is listed")
    public void totalAmountOfOrderIsListed() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalOrderAmount));
        Thread.sleep(2000);
        String totalAmountOfOrder = driver.findElement(totalOrderAmount).getText();
        System.out.println(totalAmountOfOrder);
    }

    @And("the browser closes")
    public void theBrowserCloses() {
        closeBrowser();
    }
}