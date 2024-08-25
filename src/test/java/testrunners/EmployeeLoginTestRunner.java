package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class EmployeeLoginTestRunner extends Setup {

    LoginPage loginPage;

    @Test(priority = 1, description = "Employee logins using wrong credentials")
    public void doLoginUsingWrongCreds1() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        JSONArray empArr = Utils.readJSONFile();
        JSONObject empObj = (JSONObject) empArr.get(empArr.size() - 1);
        String username = (String) empObj.get("username");

        loginPage.doLogin(username, "user123#");

        WebElement alertTextElem = driver.findElement(By.className("oxd-text--p"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertTextElem.getText(), "Invalid credentials");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Employee logins using valid credentials")
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        String expectedFormTitle = driver.findElement(By.className("orangehrm-login-title")).getText();
        String actualFormTitle = "Login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualFormTitle.contains(expectedFormTitle));

        JSONArray empArr = Utils.readJSONFile();
        JSONObject empObj = (JSONObject) empArr.get(empArr.size() - 1);
        String username = (String) empObj.get("username");
        String password = (String) empObj.get("password");
        String firstName = (String) empObj.get("firstName");
        String lastName = (String) empObj.get("lastName");

        loginPage.doLogin(username, password);

        String expectedFullName = firstName + " " + lastName;
        String actualFullName = driver.findElement(By.className("oxd-userdropdown-name")).getText();

        softAssert.assertEquals(actualFullName, expectedFullName);
        softAssert.assertAll();

//        softAssert.assertTrue(loginPage.btnProfileIcon.isDisplayed());
//        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Admin logs out of the account")
    public void doLogout() {
        loginPage = new LoginPage(driver);
        loginPage.doLogout();

        String expectedFormTitle = driver.findElement(By.className("orangehrm-login-title")).getText();
        String actualFormTitle = "Login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(expectedFormTitle.contains(actualFormTitle));
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "The logged out user gets redirected to the landing page after clicking the back button")
    public void goBack() {
        driver.navigate().back();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUrl, expectedUrl);
        softAssert.assertAll();
    }
}
