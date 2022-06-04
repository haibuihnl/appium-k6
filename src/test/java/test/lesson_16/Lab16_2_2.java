package test.lesson_16;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lab16_2_2 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);
        try {
            // NARRODOWSEARCHINGSCOPE
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();
            //Caculate Touch points
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;
            int yStartPoint = 0;
            int yEndPoint = 70 * screenHeight / 100;

            //Convert Coordinate to point option
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            //Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);
            touchAction.longPress(startPoint).moveTo(endPoint).release().perform();

            Map<String,String> notification = new HashMap<>();
            List<MobileElement> notificationElems = appiumDriver.findElements(MobileBy.id("android:id/status_bar_latest_event_content"));
            for (MobileElement notificationElem : notificationElems) {
               MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/app_name_text"));
               String titleText = titleElem.getText();
               MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/header_text"));
               String contentText = contentElem.getText();

               notification.put(titleText,contentText);
            }
            if (notification.keySet().isEmpty()){
                throw new RuntimeException("[ERR] there is no notification to test");
            }else{
                for (Object title : notification.keySet()) {
                    System.out.println("Title : "+ title);
                    System.out.println("Content : "+ notification.get(title));
                }
            }
            //Swipe up to dismiss notification bar
            touchAction.longPress(endPoint).moveTo(startPoint).release().perform();
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }

    }
}
