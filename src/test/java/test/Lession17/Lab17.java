package test.Lession17;

import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import driver.Platforms;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Lab17 {
    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platforms.android);

        try {

            //Hybrid Context
            By webviewNavBtn = MobileBy.AccessibilityId("Webview");
            MobileElement webviewBtn = appiumDriver.findElement(webviewNavBtn);
            webviewBtn.click();

            //Wait until we have more than one contexts
            WebDriverWait wait = new WebDriverWait(appiumDriver, 15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));
            //Get all context names
            appiumDriver.getContextHandles().forEach(context -> {
                System.out.println(context);
            });

            appiumDriver.context(Contexts.WEB_VIEW);
            //Interact on webview elements
            WebElement navToogleBtn = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToogleBtn.click();

            List<MobileElement> menuItemElems = appiumDriver.findElementsByCssSelector(".menu__list li");
            List<MenuItemData> menuItemDataList = new ArrayList<>();
            if (menuItemElems.isEmpty()) {
                throw new RuntimeException("EER There is no list items !");
            }
            for (MobileElement menuItemElem : menuItemElems) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");
                if (itemText.isEmpty()) {
                    menuItemDataList.add(new MenuItemData("Github", itemHref));

                } else
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
            }
            menuItemDataList.forEach(menuItemData -> {
                System.out.println(menuItemData.getName());
                System.out.println(menuItemData.getHref());
            });
// Switch back to NATIVE context
appiumDriver.context(Contexts.NATIVE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            appiumDriver.quit();
        }
    }

    public static class MenuItemData {
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }
    }
}
