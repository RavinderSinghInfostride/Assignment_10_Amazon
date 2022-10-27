package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private HomePage homePage;
    private SearchProductDisplayPage searchProductDisplayPage;
    private ProductDescriptionPage productDescriptionPage;
    private CartPage cartPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public SearchProductDisplayPage getSearchProductDisplayPage() {
        if (searchProductDisplayPage == null) {
            searchProductDisplayPage = new SearchProductDisplayPage(driver);
        }
        return searchProductDisplayPage;
    }

    public ProductDescriptionPage getProductDescriptionPage() {
        if (productDescriptionPage == null) {
            productDescriptionPage = new ProductDescriptionPage(driver);
        }
        return productDescriptionPage;
    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }
}