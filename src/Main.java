

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;

public class Main{


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        

        HashMap<String, Object> images = new HashMap<String, Object>();
        images.put("images", 2);
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values", images);

        ChromeOptions chOptions =new ChromeOptions();
        chOptions.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(chOptions);

        /**
         * temp WebElement in case it cannot find match; defaults to Supreme homepage
         */
        WebElement item = driver.findElement(By.className("logo"));

        /**
         * insert user.properties
         */
        String category = "sweatshirts";
        String keyword = "Champion® Hooded";
        String color = "Ash Grey";

        driver.get("http://www.supremenewyork.com/shop/all/"+category);

        ArrayList<WebElement> keywordElements = new ArrayList<>(driver.findElements(By.partialLinkText(keyword)));
        ArrayList<WebElement> colorElements = new ArrayList<>(driver.findElements(By.partialLinkText(color)));




        if(!color.isEmpty())
            for (WebElement temp1:keywordElements)
                for (WebElement temp2:colorElements)
                    if((temp1.getAttribute("href").equals( temp2.getAttribute("href"))))
                        item = temp1;
        else
            item = driver.findElement(By.partialLinkText(keyword));

        item.click();


    }
}
