package Pages;

import Enums.HomePage_Enums;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePage {
    WebDriver driver;

    String searchBar = "//div[@class='%s']//input[contains(@class,'nav-progressive-attribute')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchProduct() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/TestData/TestDataAmazon.xlsx";
        FileInputStream testData = null;
        try {
            testData = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(testData);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String mobileToSearch = sheet.getRow(1).getCell(0).getStringCellValue();
        driver.findElement(By.xpath(String.format(searchBar, HomePage_Enums.SearchBarInputBox.getResourcesName()))).sendKeys(mobileToSearch);
        driver.findElement(By.xpath(String.format(searchBar, HomePage_Enums.SearchButton.getResourcesName()))).click();
    }
}