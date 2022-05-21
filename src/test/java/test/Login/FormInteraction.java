package test.Login;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormInteraction {
    public static void main(String[] args) {

        //Get appium driver
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);
        try {

            // Fill and click on nav login button
            MobileElement loginButtonNav = driver.findElement(MobileBy.AccessibilityId("test/Login"));
            loginButtonNav.click();

            // Fill the form
            MobileElement userName = driver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement password = driver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtn = driver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            userName.sendKeys("hai@gmail.com");
            password.sendKeys("12345@qA");
            loginBtn.click();

            //Get text from dialog
            WebDriverWait wait = new WebDriverWait(driver,5L);
            WebElement loginDialogTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));
            System.out.println("Title : " + loginDialogTitle.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Quit appium session
            driver.quit();


        }
    }
}
