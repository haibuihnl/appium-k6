package test.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class ParameterTest {

    @Test
    @Parameters({"systemPort", "udid"})
    public void testTestNGParams(String hai, String thanh) {
        System.out.println(new GregorianCalendar().getTime());
        System.out.println(hai + " || " + thanh);
    }
}