package com.cdecube.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @Author: liupeng
 * @Description:封装浏览器选择器
 * @Date: Created in 15:45 2018/6/29
 * @Modified By:
 */
public class selectDriver {
    public WebDriver driverName(String browser) {
        if (browser.equalsIgnoreCase("fireFox")) {
            System.setProperty("webdriver.chrome.driver","src/test/resources/browserdriver/geckodriver.exe");
            return new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.chrome.driver","src/test/resources/browserdriver/IEDriverServer.exe");
            return new InternetExplorerDriver();
        }else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","src/test/resources/browserdriver/chromedriver.exe");
            return new ChromeDriver();
        }else {
            System.setProperty("webdriver.chrome.driver","src/test/resources/browserdriver/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            return new ChromeDriver(chromeOptions);
        }
    }
}
