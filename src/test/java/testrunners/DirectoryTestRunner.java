package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DirectoryPage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class DirectoryTestRunner extends Setup {
    DirectoryPage directoryPage;
    SoftAssert softAssert;

    @BeforeTest
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test(priority = 1, description = "Search an employee using a non-existing employee name")
    public void searchUsingNonExistingEmployeeName() throws InterruptedException {
        directoryPage = new DirectoryPage(driver);
        directoryPage.leftMenu.get(8).click();
        directoryPage.searchEmployeeByName("jkljkljkl");
        Thread.sleep(5000);

        String actualMessage = driver.findElement(By.cssSelector("[role=listbox]")).getText();
        String expectedMessage = "No Records Found";

        softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage, expectedMessage);
    }

    @Test(priority = 2, description = "Search an employee using a employee name")
    public void searchUsingEmployeeName() throws InterruptedException, IOException, ParseException {
        directoryPage = new DirectoryPage(driver);
        directoryPage.leftMenu.get(8).click();

        JSONArray empArr = (JSONArray) Utils.readJSONFile();
        JSONObject empObj = (JSONObject) empArr.get(empArr.size() - 1);
//        String empName = (String) empObj.get("firstName") + " " + (String) empObj.get("lastName");
        String empName = (String) empObj.get("firstName");

        directoryPage.searchEmployeeByName(empName);
        Thread.sleep(5000);

        String actualName = driver.findElement(By.cssSelector("[role=listbox]")).getText();
        softAssert = new SoftAssert();
        softAssert.assertEquals(actualName, empName);
    }

    @AfterTest
    public void doLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }
}
