package lession17;

import custom_explicit_wait.WaitMoreThanOneContext;
import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HybridContext {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);
        try {
            By webviewNavSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnEle = appiumDriver.findElement(webviewNavSel);
            webviewNavBtnEle.click();
            //Wait until we have more than one contexts

            WebDriverWait wait = new WebDriverWait(appiumDriver, 15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));
            //Get all context namws
            for (String context : appiumDriver.getContextHandles()) {
                System.out.println(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }
}
