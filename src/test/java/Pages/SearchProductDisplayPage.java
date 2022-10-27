package Pages;

import Enums.SearchProductDisplayPage_Enums;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;

public class SearchProductDisplayPage {
    WebDriver driver;

    String productToAddToCart = "(//span[contains(text(),'%s')])[1]";

    public SearchProductDisplayPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectTheProductToAddToCart() {
        driver.findElement(By.xpath(String.format(productToAddToCart, SearchProductDisplayPage_Enums.ProductName.getResourcesName()))).click();
        getWindowHandle();
    }

    private void getWindowHandle() {
        String mainWindow = driver.getWindowHandle();
        ArrayList<String> childTabWindow = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(String.valueOf(childTabWindow.get(1)));
    }
}