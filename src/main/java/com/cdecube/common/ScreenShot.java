package com.cdecube.common;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liupeng
 * @Description:
 * @Date: Created in 14:35 2018/7/16
 * @Modified By:
 */
public class ScreenShot {
    public WebDriver driver;

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenshot(String screenPath) {
        try {
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(screenPath));
        } catch (IOException e) {
            System.out.println("Screen shot error: " + screenPath);
        }
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }

    public void takeScreenshot(String classname, String methodname) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        String screenName = classname+"_"+methodname+"_"+dateString+"_"+ ".jpg";
        System.out.println(screenName);
        File dir = new File("test-output/snapshot");
        if (!dir.exists())
            dir.mkdirs();
        String screenPath = dir.getAbsolutePath() + "/" + screenName;
        this.takeScreenshot(screenPath);
    }
}