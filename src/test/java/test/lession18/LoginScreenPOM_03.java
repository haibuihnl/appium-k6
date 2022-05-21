package test.lession18;

import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.BottomNavComponent;
import models.components.LoginFormComponent;
import models.pages.LoginScreen;

public class LoginScreenPOM_03 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = DriverFactory.getDriver(Platforms.android);

        try {
            LoginScreen loginScreen = new LoginScreen(driver);
            BottomNavComponent bottomNavComponent = loginScreen.bottomNavComponent();

            // Find and click on nav login button
            bottomNavComponent.clickOnLoginIcon();

            // Fill the form
            LoginFormComponent loginFormComponent = loginScreen.loginFormComponent();
            loginFormComponent.inputUsername("teo@sth.com");
            loginFormComponent.inputPassword("12345678");
            loginFormComponent.clickOnLoginBtn();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
