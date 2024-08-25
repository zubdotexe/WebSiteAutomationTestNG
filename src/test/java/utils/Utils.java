package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setup.Setup;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    public static JSONArray readJSONFile() throws IOException, ParseException {
        String filePath = "./src/test/resources/users.json";

        JSONParser jsonParser = new JSONParser();
        JSONArray empArr = (JSONArray) jsonParser.parse(new FileReader(filePath));

        return empArr;
    }

    public static void writeJSONFile(JSONArray empArr) throws IOException {
        String filePath = "./src/test/resources/users.json";

        FileWriter writer = new FileWriter(filePath);
        writer.write(empArr.toJSONString());
        writer.flush();
    }

    public static void saveUsers(Setup.EmployeeModel model) throws IOException, ParseException {

//        String filePath = "./src/test/resources/users.json";
//        JSONParser jsonParser = new JSONParser();
        JSONArray empArr = readJSONFile();

        JSONObject empObj = new JSONObject();
        empObj.put("firstName", model.getFirstName());
        empObj.put("lastName", model.getLastName());
        empObj.put("employeeId", model.getEmployeeId());
        empObj.put("username", model.getUsername());
        empObj.put("password", model.getPassword());

        empArr.add(empObj);

        writeJSONFile(empArr);
    }

    public static void allCharsRemover(WebElement elem) {
        elem.sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
    }

    public static void scroll(WebDriver driver, int height) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+height+")");
    }
}
