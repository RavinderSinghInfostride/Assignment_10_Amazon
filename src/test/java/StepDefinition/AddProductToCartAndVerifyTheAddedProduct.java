package StepDefinition;

import Pages.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

public class AddProductToCartAndVerifyTheAddedProduct extends BaseClass {
    @Given("user is on the home page of amazon")
    public void user_is_on_the_home_page_of_amazon() throws IOException {
        setup();
    }

    @And("user searches for a mobile")
    public void user_searches_for_a_mobile() throws IOException {
        pageFactory.getHomePage().searchProduct();
    }

    @And("user selects the mobile and the mobile name is displayed on the console")
    public void user_selects_the_mobile_and_the_mobile_name_is_displayed_on_the_console() {
        pageFactory.getSearchProductDisplayPage().selectTheProductToAddToCart();
        pageFactory.getProductDescriptionPage().searchedProductName();
    }

    @And("the size and colour variations available for mobile are displayed on console")
    public void the_size_and_colour_variations_available_for_mobile_are_displayed_on_console() {
        pageFactory.getProductDescriptionPage().searchedProductSize();
        pageFactory.getProductDescriptionPage().searchedProductColour();
    }

    @And("user navigates to question and answer page and top three answers are listed")
    public void user_navigates_to_question_and_answer_page_and_top_three_answers_are_listed() throws InterruptedException {
        pageFactory.getProductDescriptionPage().questionAndAnswerList();
    }

    @When("user adds product to cart")
    public void user_adds_product_to_cart() {
        pageFactory.getProductDescriptionPage().addProductToCart();
    }

    @Then("verify the product added to cart is correct")
    public void verify_the_product_added_to_cart_is_correct() {
        pageFactory.getCartPage().verifyProductAddedToCart();
    }

    @And("user increases product quantity to four")
    public void userIncreasesProductQuantityToFour() {
        pageFactory.getCartPage().increaseProductQuantity();
    }

    @Then("verify product quantity")
    public void verifyProductQuantity() {
        pageFactory.getCartPage().verifyQuantityOfProduct();
    }

    @And("total amount of order is listed")
    public void totalAmountOfOrderIsListed() throws InterruptedException {
        pageFactory.getCartPage().listTotalOrderAmount();
    }
}