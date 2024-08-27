package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

//        accessing the website
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

//    public static class EmployeeModel {
//        String firstName;
//        String lastName;
//        String employeeId;
//        String username;
//        String password;
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        public String getEmployeeId() {
//            return employeeId;
//        }
//
//        public void setEmployeeId(String employeeId) {
//            this.employeeId = employeeId;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public EmployeeModel(String firstName, String lastName, String employeeId, String username, String password) {
//            this.firstName = firstName;
//            this.lastName = lastName;
//            this.employeeId = employeeId;
//            this.username = username;
//            this.password = password;
//        }
//
//        public EmployeeModel() {}
//    }
}
