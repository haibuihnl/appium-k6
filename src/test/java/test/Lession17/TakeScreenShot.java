package test.Lession17;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;

public class TakeScreenShot {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);

        //Add one more dependency:
        try {
            MobileElement login = appiumDriver.findElement(MobileBy.AccessibilityId("test/Login"));
            login.click();
            // Whole screen
            File base64ScreenShotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshot/").concat("Homescreen.png");
            FileUtils.copyFile(base64ScreenShotData, new File(fileLocation));
            //An area
            MobileElement loginForm = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            File base64LoginFormData = loginForm.getScreenshotAs(OutputType.FILE);
            String loginFormFileLocation = System.getProperty("user.dir").concat("/screenshot/").concat("LoginForm.png");
            FileUtils.copyFile(base64LoginFormData, new File(loginFormFileLocation));
            //An element
            MobileElement loginBtn = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            File base64LoginBtnData = loginBtn.getScreenshotAs(OutputType.FILE);
            String loginBtnFileLocation = System.getProperty("user.dir").concat("/screenshot/").concat("LoginBtn.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnFileLocation));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
