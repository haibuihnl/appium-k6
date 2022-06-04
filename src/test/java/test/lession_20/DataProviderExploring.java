package test.lession_20;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DataProviderExploring {

    @Test(dataProvider = "hai")
    public void testSth(String text){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
        softAssert.assertFalse(true);
        System.out.println(text);
        softAssert.assertAll();
    }


    @DataProvider(name = "hai")
    public String[] getDataSet(){
        return new String[]{"Text 01", "Text 02", "Text 03"};
    }

    @DataProvider(name = "thanh")
    public String[] getDataSet_(){
        return new String[]{"Text 01_TI", "Text 02_TI", "Text 03_TI"};
    }
}