package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.Setup;
import utils.Utils;

import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenu;

    @FindBy(className = "oxd-button")
    public List<WebElement> btnAdd;

    @FindBy(className = "oxd-switch-input")
    public WebElement btnToggle;

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;

    @FindBy(className = "oxd-input")
    public List<WebElement> textField;

    @FindBy(className = "oxd-input--active")
    public List<WebElement> textFieldSearchEmpId;

    @FindBy(className = "oxd-autocomplete-text-input--active")
    public List<WebElement> getTextFieldSearchEmpName;

    public PIMPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNewEmployee(String firstName, String lastName, String employeeId, String username, String password, String confirmPass) {

        textField.get(1).sendKeys(firstName);
        textField.get(3).sendKeys(lastName);

        Utils.allCharsRemover(textField.get(4));
        textField.get(4).sendKeys(employeeId);

        textField.get(5).sendKeys(username);
        textField.get(6).sendKeys(password);
        textField.get(7).sendKeys(confirmPass);

        btnSubmit.click();
    }

    public void createNewEmployee(Setup.EmployeeModel model) {

        textField.get(1).sendKeys(model.getFirstName());
        textField.get(3).sendKeys(model.getLastName());

        Utils.allCharsRemover(textField.get(4));
        textField.get(4).sendKeys(model.getEmployeeId());
        textField.get(5).sendKeys(model.getUsername());
        textField.get(6).sendKeys(model.getPassword());
        textField.get(7).sendKeys(model.getPassword());

        btnSubmit.click();
    }

    public void searchEmployeeByID(String employeeId) {
        textFieldSearchEmpId.get(1).sendKeys(employeeId);
        btnSubmit.click();
    }

}
