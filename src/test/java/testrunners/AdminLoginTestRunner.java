package testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;

public class AdminLoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Admin logins using wrong username and password")
    public void doLoginUsingWrongCreds1() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("user1", "aDmiN1");

        WebElement alertTextElem = driver.findElement(By.className("oxd-text--p"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertTextElem.getText(), "Invalid credentials");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Admin logins using correct password in Capital letters")
    public void doLoginUsingWrongCreds2() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("ADMIN", "admin123");

        WebElement alertTextElem = driver.findElement(By.className("oxd-text"));

        String actualAlertText = alertTextElem.getText();
        String expectedAlertText = "Invalid credentials";

        System.out.println(actualAlertText);
        System.out.println(expectedAlertText);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualAlertText, expectedAlertText);

        loginPage.doLogout();

        softAssert.assertAll();
    }
    @Test(priority = 3, description = "Admin logins using valid credentials")
    public void doLogin() {
        loginPage = new LoginPage(driver);

        String expectedFormTitle = driver.findElement(By.className("orangehrm-login-title")).getText();
        String actualFormTitle = "Login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualFormTitle.contains(expectedFormTitle));

        loginPage.doLogin("Admin", "admin123");

        softAssert.assertTrue(loginPage.btnProfileIcon.isDisplayed());
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Admin logs out of the account")
    public void doLogout() {
        loginPage = new LoginPage(driver);
        loginPage.doLogout();

        String expectedFormTitle = driver.findElement(By.className("orangehrm-login-title")).getText();
        String actualFormTitle = "Login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(expectedFormTitle.contains(actualFormTitle));
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "The logged out user gets redirected to the landing page after clicking the back button")
    public void goBack() {
        driver.navigate().back();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUrl, expectedUrl);
        softAssert.assertAll();
    }
}
