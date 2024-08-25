package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MyInfoPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.Random;

public class MyInfoTestRunner extends Setup {
    MyInfoPage myInfoPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        JSONArray empArr = Utils.readJSONFile();
        JSONObject empObj = (JSONObject) empArr.get(empArr.size() - 1);
        String username = (String) empObj.get("username");
        String password = (String) empObj.get("password");

//        String firstName = (String) empObj.get("firstName");
//        String lastName = (String) empObj.get("lastName");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(username, password);

//        String expectedFullName = firstName + " " + lastName;
//        String actualFullName = driver.findElement(By.className("oxd-userdropdown-name")).getText();
//
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(actualFullName, expectedFullName);
//        softAssert.assertAll();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.btnProfileIcon.isDisplayed());
        softAssert.assertAll();
    }

    public void goToMyInfo() {
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.leftMenu.get(2).click();

        String actualTitle = driver.findElements(By.className("oxd-text--h6")).get(2).getText();
        String expectedTitle = "Personal Details";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, expectedTitle);
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "Employee selects multiple radio buttons for gender")
    public void selectMultipleGender() throws InterruptedException {
        goToMyInfo();
        Utils.scroll(driver, 200);
        Thread.sleep(4000);

        myInfoPage.radioBtnGender.get(0).click();
        myInfoPage.radioBtnGender.get(1).click();
//        myInfoPage.radioBtnMale.click();
//        myInfoPage.radioBtnFemale.click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!driver.findElement(By.xpath("//input[contains(@value, \"1\")]")).isSelected());
        softAssert.assertTrue(driver.findElement(By.xpath("//input[contains(@value, \"2\")]")).isSelected());
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Employee selects their preferred gender")
    public void selectGender() throws InterruptedException {
        goToMyInfo();
        Utils.scroll(driver, 200);
        Thread.sleep(4000);

        Random rand = new Random();
        int preference = rand.nextInt(2);
        myInfoPage.radioBtnGender.get(preference).click();
        myInfoPage.btnSave.get(0).click();
        driver.navigate().refresh();

        Thread.sleep(5000);
        Utils.scroll(driver, 200);
        Thread.sleep(4000);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.xpath("//input[contains(@value, " + (preference+1) + ")]")).isSelected());
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Employee selects multiple blood type")
    public void selectMultipleBloodType() throws InterruptedException {
        goToMyInfo();
        Utils.scroll(driver, 800);
        Thread.sleep(4000);

        myInfoPage.dropdownBloodType.get(2).sendKeys("o");
        myInfoPage.dropdownBloodType.get(2).sendKeys(Keys.ENTER);

        myInfoPage.dropdownBloodType.get(2).sendKeys("a");
        myInfoPage.dropdownBloodType.get(2).sendKeys(Keys.ENTER);
        myInfoPage.btnSave.get(1).click();

//        driver.navigate().refresh();
        Thread.sleep(4000);
//        utils.Utils.scroll(driver, 800);

        String actualBloodType = myInfoPage.dropdownBloodType.get(2).getText();
        String expectedBloodType = "A+";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!actualBloodType.contains("O+"));
        softAssert.assertEquals(actualBloodType, expectedBloodType);
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Employee selects their preferred blood type")
    public void selectBloodType() throws InterruptedException {
        goToMyInfo();
        Utils.scroll(driver, 800);
        Thread.sleep(4000);

        myInfoPage.dropdownBloodType.get(2).sendKeys("o");
        myInfoPage.dropdownBloodType.get(2).sendKeys(Keys.ENTER);
        myInfoPage.btnSave.get(1).click();

        driver.navigate().refresh();
        Thread.sleep(4000);
        Utils.scroll(driver, 800);

        String actualBloodType = myInfoPage.dropdownBloodType.get(2).getText();
        String expectedBloodType = "O+";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualBloodType, expectedBloodType);
        softAssert.assertAll();
    }

    @AfterTest
    public void doLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }
}
