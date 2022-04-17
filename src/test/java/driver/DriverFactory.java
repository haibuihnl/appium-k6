package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx {

    public static AppiumDriver<MobileElement> getDriver(Platforms platforms) {

        if (platforms == null) {
            throw new IllegalArgumentException("Platform can't be null, you can provide on of there: " + Arrays.toString(Platforms.values()));
        }

        AppiumDriver<MobileElement> appiumDriver = null;
        Exception exception = null;
        try {

            //Desired Capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(UDID, "32009220c030a589");
//            desiredCapabilities.setCapability(UDID, "emulator-5554");
            desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");


            // Init appium session
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            switch (platforms) {
                case android:
                    appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
                    break;
                case ios:
                    appiumDriver = new IOSDriver<MobileElement>(appiumServer, desiredCapabilities);
            }


        } catch (Exception e) {
            exception = e;
        }
        if (appiumDriver == null) {
            throw new RuntimeException("Could not");
        }
        //Add IMPLICIT WAIT HERE
        appiumDriver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
        return appiumDriver;
    }
}
