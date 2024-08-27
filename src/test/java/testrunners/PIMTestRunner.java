package testrunners;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import setup.EmployeeModel;
import utils.Utils;

import java.io.IOException;

public class PIMTestRunner extends Setup {
    PIMPage pimPage;
    SoftAssert softAssert;

    @BeforeTest
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    public void goToCreateEmployee() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenu.get(1).click();

        Thread.sleep(3000);
        String actualText = driver.findElements(By.className("oxd-text")).get(14).getText();
        String expectedText = "Records Found";

        softAssert = new SoftAssert();
        softAssert.assertTrue(actualText.contains(expectedText));
        softAssert.assertAll();

        pimPage.btnAdd.get(2).click();

        pimPage.btnToggle.click();
    }

    @Test(priority = 1, description = "Create an employee without filling in the mandatory text fields")
    public void createEmployeeWOUsername() throws InterruptedException {
        goToCreateEmployee();

        pimPage.createNewEmployee("Anna", "Ken", "", "", "Ann@567", "Ann@567");
        pimPage.btnSubmit.click();

        String actualMessage = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String expectedMessage = "Required";

        softAssert.assertEquals(actualMessage, expectedMessage);
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Create an employee using an existing employee id")
    public void createEmployeeUsingExistingEmpId() throws InterruptedException {
        goToCreateEmployee();

        pimPage.createNewEmployee("Anna", "Ken", "0367", "anna_ken", "Ann@567", "Ann@567");
        pimPage.btnSubmit.click();

        String actualMessage = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String expectedMessage = "Employee Id already exists";

        softAssert.assertEquals(actualMessage, expectedMessage);
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Create an employee using an existing username")
    public void createEmployeeUsingExistingUsername() throws IOException, ParseException, InterruptedException {
        JSONArray empArr = Utils.readJSONFile();
        JSONObject empObj = (JSONObject) empArr.get(empArr.size() - 1);
        String username = (String) empObj.get("username");

        goToCreateEmployee();

        pimPage.createNewEmployee("Anna", "Ken", "", username, "Ann@567", "Ann@567");
        pimPage.btnSubmit.click();

        String actualMessage = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String expectedMessage = "Username already exists";

        softAssert.assertEquals(actualMessage, expectedMessage);
        softAssert.assertAll();
    }


    @Test(priority = 4, description = "Create an employee filling in all the mandatory fields")
    public void createEmployeeUsingValidInfo() throws InterruptedException, IOException, ParseException {
        goToCreateEmployee();

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        int min = 100;
        int max = 500;
        int range = max - min + 1;
        String employeeId = Integer.toString((int) (Math.random() * range) + min);

        String username = firstName.toLowerCase() + "_" + lastName.toLowerCase();
        String password = "@" + firstName + "#" + Integer.toString((int) (Math.random() * 100));

        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setEmployeeId(employeeId);
        employeeModel.setUsername(username);
        employeeModel.setPassword(password);

        pimPage.createNewEmployee(employeeModel);

        Thread.sleep(5000);

//        WebElement profileNameElem = driver.findElements(By.className("oxd-text--h6")).get(1);
//        String actualProfileName = profileNameElem.getText();
//        String expectedProfileName = firstName + " " + lastName;

        String actualTitle = driver.findElements(By.className("oxd-text--h6")).get(2).getText();
        String expectedTitle = "Personal Details";
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
//        wait.until(ExpectedConditions.visibilityOf(profileNameElem));

        softAssert.assertEquals(actualTitle, expectedTitle);
        softAssert.assertAll();

        Utils.saveUsers(employeeModel);
    }

    @Test(priority = 5, description = "Search an employee using a non-existing employee id")
    public void searchUsingNonExistingEmployeeId() throws IOException, ParseException, InterruptedException {
        pimPage.leftMenu.get(1).click();

        pimPage.searchEmployeeByID("9798734");

        Thread.sleep(4000);
        String actualMessage = driver.findElements(By.className("oxd-text--span")).get(12).getText();
        String expectedMessage = "No Records Found";

        softAssert.assertEquals(actualMessage, expectedMessage);
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Search an employee using employee id")
    public void searchUsingEmployeeId() throws IOException, ParseException, InterruptedException {
        JSONArray jsonArr = Utils.readJSONFile();

        JSONObject empObj = (JSONObject) jsonArr.get(jsonArr.size() - 1);
        String searchedEmployeeId = (String) empObj.get("employeeId");

        pimPage.leftMenu.get(1).click();
        pimPage.searchEmployeeByID(searchedEmployeeId);
        Thread.sleep(4000);

        String actualEmployeeId = driver.findElements(By.className("oxd-table-cell")).get(1).getText();
        softAssert.assertEquals(searchedEmployeeId, actualEmployeeId);
        softAssert.assertAll();
    }

    @AfterTest
    public void doLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }
}
