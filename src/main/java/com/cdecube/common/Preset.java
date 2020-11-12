package com.cdecube.common;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @Author: liupeng
 * @Description:浏览器设置
 * @Date: Created in 16:26 2018/6/13
 * @Modified By:
 */
public class Preset {
    public static WebDriver driver;
    public  Preset(String browser) {
        selectDriver SelectDriver = new selectDriver();
        this.driver = SelectDriver.driverName(browser);
    }

    /**
     * @Description: 获取driver
     * @param: []
     * @return: org.openqa.selenium.WebDriver
     */
    public static WebDriver getDriver() {
        return driver;
    }
    /**
     * @Description:隐式等待
     * @param: []
     * @return: void
     */
    public void timeout(int num) {
        driver.manage().timeouts().implicitlyWait(num, TimeUnit.SECONDS);
    }
    /**
     * @Description: 窗口最大化
     * @param: []
     * @return: void
     */
    public void maxwind() {
        driver.manage().window().maximize();
    }
    /*
     * @Description: 关闭窗口
     * @param: []
     * @return: void
     */
    public void quit() {
        driver.quit();
    }
}
