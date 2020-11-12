package com.cdecube.common;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @Author: liupeng
 * @Description:
 * @Date: Created in 16:20 2018/7/16
 * @Modified By:
 */
public class DotTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            super.onTestFailure(tr);
            WebDriver driver = Preset.getDriver();
            ScreenShot screenShot = new ScreenShot(driver);
            // 类名为全类名，包含包名
            String classname = tr.getTestClass().getName();
            // 方法名为执行的方法
            String methodname = tr.getMethod().getMethodName();
            screenShot.takeScreenshot(classname,methodname);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}