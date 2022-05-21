package test.lession16;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Lab16_1 {
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

            // Go to Forms page
            MobileElement formNavElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            formNavElem.click();
            Thread.sleep(2000);

            MobileElement dropDown = appiumDriver.findElement(MobileBy.xpath("//android.widget.EditText[@text=\"Select an item...\"]"));
//            List<MobileElement> dropDownList = appiumDriver.findElementsByXPath(".//android.widget.EditText[@text=\"Select an item...\"]");
            //Verify form title
            MobileElement formTitleElem = appiumDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text=\"Form components\"]"));
            formTitleElem.isDisplayed();
            // Input field
            MobileElement inputFieldTextbox = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            inputFieldTextbox.sendKeys("abcd");
            // Verify text after input
            MobileElement inputTextResult = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));
            inputTextResult.getText().contains("abcd");
            Thread.sleep(2000);
            // Click Switch button
            MobileElement switchBtn = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            switchBtn.click();
            //Verify text message after switch
            MobileElement switchText = appiumDriver.findElement(MobileBy.AccessibilityId("switch-text"));
            switchText.getText().contains("Click to turn the switch OFF");
            Thread.sleep(2000);
            //Select dropdown
            dropDown.click();
            //Select item in dropdown
            MobileElement itemSelected = appiumDriver.findElement(MobileBy.xpath("//android.widget.CheckedTextView[@index=\"2\"]"));
            itemSelected.click();
            Thread.sleep(2000);
            // Click Active button
            MobileElement activeBtn = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtn.click();
            // Verify dialog show
            MobileElement dialogDetail = appiumDriver.findElement(MobileBy.xpath("//android.widget.CheckedTextView[@text=\"This button is active\"]"));
            dialogDetail.isDisplayed();
            Thread.sleep(2000);
            // Click OK
            MobileElement OKBtn = appiumDriver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"OK\"]"));
            OKBtn.click();

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
