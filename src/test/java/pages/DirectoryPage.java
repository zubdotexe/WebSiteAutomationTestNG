package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DirectoryPage {
    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenu;

    @FindBy(xpath = "//input[contains(@placeholder, 'Type for hints...')]")
    public WebElement textFieldSearchEmpId;

    public DirectoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchEmployeeByName(String name) {
        textFieldSearchEmpId.sendKeys(name);

    }
}
