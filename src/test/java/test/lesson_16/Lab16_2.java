package test.lesson_16;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Lab16_2 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);
        try {
            //Desired capabilites
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("platformVersion", "10");
            desiredCapabilities.setCapability("deviceName", "32009220c030a589");
            desiredCapabilities.setCapability("automationName", "UiAutomator2");
            desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
            desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

            //Init appium sever
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AppiumDriver<MobileElement>(appiumServer, desiredCapabilities);
            // SWIPE THE SCREEN
            // Go to Swipe screen
            MobileElement swipeBtnNav = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            swipeBtnNav.click();
            // Wait util on Swipe screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//android.widget.TextView[@text=\"Swipe horizontal\"]")));
            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            //Caculate Touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 10 * screenWidth / 100;
            int yStartPoint = 70 * screenHeight / 100;
            int yEndPoint = 70 * screenHeight / 100;

            //Convert Coordinate to point option
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Using TouchAction to swipe

            TouchAction touchAction = new TouchAction(appiumDriver);
            for (int i = 0; i < 5; i++) {
                touchAction.longPress(startPoint).moveTo(endPoint).release().perform();
            }

            Thread.sleep(5000);
            //Verify last vertical card
            MobileElement compatibleCard = appiumDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text=\"COMPATIBLE\"]"));
            compatibleCard.isDisplayed();

            int x2StatPoint = 50 * screenWidth / 100;
            int x2Endpoint = 50 * screenWidth / 100;
            int y2StatPoint = 90 * screenWidth / 100;
            int y2EndPoint = 0;

            PointOption startPoint2 = new PointOption().withCoordinates(x2StatPoint, y2StatPoint);
            PointOption endPoint2 = new PointOption().withCoordinates(x2Endpoint, y2EndPoint);
            for (int i = 0; i < 3 ; i++) {

                touchAction.longPress(startPoint2).moveTo(endPoint2).release().perform();
            }

            //Verify scroll to end of the screen
            MobileElement foundMessage = appiumDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text=\"You found me!!!\"]"));
            foundMessage.getText().contains("You found me!!!");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
