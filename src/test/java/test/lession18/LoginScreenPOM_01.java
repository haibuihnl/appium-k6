package test.lession18;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreen;

public class LoginScreenPOM_01 {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);

        try {
            LoginScreen loginScreen = new LoginScreen(driver);

            // Find and click on nav login button
            loginScreen.bottomNavComponent().clickOnLoginIcon();

            // Fill the form
            loginScreen.loginFormComponent().inputUsername("teo@sth.com");
            loginScreen.loginFormComponent().inputPassword("12345678");
            loginScreen.loginFormComponent().clickOnLoginBtn();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
