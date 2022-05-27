package utils;

import org.testng.Assert;

public class Asserssion {

    public void isCountSame(int expCount, int actualCount) {
        Assert.assertEquals(expCount, actualCount);
    }
    public void isTextSame(String expText, String actualText) {
        Assert.assertEquals(expText, actualText);
    }
    public void isReturnSame(boolean expText, boolean actualText) {
        Assert.assertEquals(expText, actualText);
    }
}
