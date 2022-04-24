package Login;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Dimension;

import java.util.List;

public class XPATHLearning {
    public static void main(String[] args) {
        //Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);

        try {
            MobileElement loginButtonNav = driver.findElement(MobileBy.AccessibilityId("Login"));
            loginButtonNav.click();

            //Find all matching elements
            List<MobileElement> credInputFieldElements = driver.findElements(MobileBy.xpath("//android.widget.EditText"));

            final int USER_NAME_INDEX = 0;
            final int PASWORD_INDEX = 1;
            credInputFieldElements.get(USER_NAME_INDEX).sendKeys("teo@sth.com");
            credInputFieldElements.get(PASWORD_INDEX).sendKeys("12345678");

            MobileElement loginIntructionEle =
                    driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            Dimension windowSize = driver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            int xStartPoint = 50 * screenHeight / 100;
            
            System.out.println(loginIntructionEle.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
