package test.form;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.form.FormFlow;

public class FormTest extends BaseTest {

    @Test
    public void testFormInput() {
        FormFlow formFlow = new FormFlow(getDriver());
        formFlow.gotoFormScreen();
        formFlow.fillTheForm();
        formFlow.verifyFormDisplay();
        Assert.fail("[ERR] ...");
    }
}