package com.cdecube.common;


import org.ho.yaml.Yaml;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author: liupeng
 * @Description:利用yaml管理页面元素
 * @Date: Created in 11:47 2018/6/13
 * @Modified By:
 */
public class Page {
    private WebDriver driver;
    private String yamlFile;
    private HashMap<String, HashMap<String, String>> ml;
    private HashMap<String, HashMap<String, String>> url;
    private HashMap<String, HashMap<String, String>> cookie;

    Screen screen = new Screen();
    public Page(WebDriver driver) {
        this.getYamFile();
        this.driver = driver;
    }

    /*
     * @Description:初始化yaml文件
     * @param: []
     * @return: void
     */
    public void getYamFile() {
        yamlFile = "locator";
        File f = new File("src/test/resources/locator/" + yamlFile + ".yaml");
        try {
            ml = Yaml.loadType(new FileInputStream(f.getAbsoluteFile()), HashMap.class);
            url = Yaml.loadType(new FileInputStream(f.getAbsoluteFile()), HashMap.class);
            cookie = Yaml.loadType(new FileInputStream(f.getAbsoluteFile()), HashMap.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * @Description:封装By
     * @param: [type, value]
     * @return: org.openqa.selenium.By
     */
    private By getBy(String type, String value) {
        By by = null;
        if (type.equals("id")) {
            by = By.id(value);
        }
        if (type.equals("name")) {
            by = By.name(value);
        }
        if (type.equals("xpath")) {
            by = By.xpath(value);
        }
        if (type.equals("className")) {
            by = By.className(value);
        }
        if (type.equals("linkText")) {
            by = By.linkText(value);
        }
        if (type.equals("tagName")) {
            by = By.tagName(value);
        }
        if (type.equals("cssSelector")) {
            by = By.cssSelector(value);
        }
        return by;
    }

    /**
     * @param by
     * @return Description:等待方法
     */
    private WebElement watiForElement(final By by, int timenum) {

        WebElement element = null;
        try {
            element = new WebDriverWait(driver, timenum)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until " + timenum + "s");
            System.out.println(e);
        }
        return element;
    }

    /*
     * @Description:获取单个元素方法
     * @param: [key]
     * @return: org.openqa.selenium.WebElement
     */
    public WebElement getElement(String key) {
        String type = ml.get(key).get("type");
        String value = ml.get(key).get("value");
        try {
            return driver.findElement(this.getBy(type, value));
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(key + ":元素不存在");
            return driver.findElement(this.getBy(type, value));
        }
    }
        /*
     * @Description:获取一组元素
     * @param: [key]
     * @return: java.util.List<org.openqa.selenium.WebElement>
     */
    public List<WebElement> getElements(String key) {
        String type = ml.get(key).get("type");
        String value = ml.get(key).get("value");
        return (List<WebElement>) driver.findElements(this.getBy(type, value));
    }
    /**
     * @param key
     * @return Description:获取单个元素等待方法
     */
    public WebElement waitgetElement(String key, int timenum) {
        String type = ml.get(key).get("type");
        String value = ml.get(key).get("value");
        //return driver.findElement(this.getBy(type, value));
        return this.watiForElement(this.getBy(type, value), timenum);
    }


    /*
     * @Description:获取固定区域下的一组元素
     * @param: [key, keys]
     * @return: java.util.List<org.openqa.selenium.WebElement>
     */
    public List<WebElement> areaGetElements(String key, String keys) {
        String type = ml.get(key).get("type");
        String value = ml.get(key).get("value");
        String types = ml.get(keys).get("type");
        String values = ml.get(keys).get("value");
        return (List<WebElement>) driver.findElement(this.getBy(type, value)).findElements(this.getBy(types, values));
    }

    /*
     * @Description:cookie使用
     * @param: []
     * @return: void
     */
    public void addCookies(String key) {
        String type = cookie.get(key).get("type");
        String value = cookie.get(key).get("value");
        Cookie c = new Cookie(type, value);
        driver.manage().addCookie(c);
        System.out.println("加载cookie");
        try {
            Thread.sleep(2000);
            System.out.println("等待两秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        System.out.println("加载cookie后刷新页面");
    }

    /*
     * @Description:封装action动作,模拟鼠标移动到元素上
     * @param: []
     * @return: void
     */
    public Actions moveElement(String key) {
        Actions action = new Actions(driver);
        return action.moveToElement(this.getElement(key));
    }

    /*
     * @Description:获取Url
     * @param: [key]
     * @return: java.lang.String
     */
    public void getUrl(String key) {
        String ur = url.get(key).get("value");
        driver.get(ur);
    }

    /**
     * @Description:判断元素是否存在
     * @param: [key]
     * @return: boolean
     */
    public boolean isDisplay(String key) {
        String type = ml.get(key).get("type");
        String value = ml.get(key).get("value");
        try {
            driver.findElement(this.getBy(type, value));
            System.out.println(key + ":元素存在");
            return true;
        } catch (Exception e) {
            System.out.println(key + ":元素不存在");
            return false;
        }
    }

    /**
     * @Description:睡眠
     * @param: [num]
     * @return: void
     */
    public void sleep(int millisecond) throws InterruptedException {
        Thread.sleep(millisecond);
        int second = millisecond / 1000;
        System.out.println("等待" + second + "秒");
    }
    /**
     * @Description: sikuli 寻找图片
     * @param:
     * @return:
     */
    public void findImage(String imageName, int time) throws IOException {
        try {
            screen.wait(this.sourcefile() + imageName,time).highlight(2);
            System.out.println("捕获到图片：" + imageName);
        } catch (FindFailed findFailed) {
            System.out.println("图片捕获失败");
            findFailed.printStackTrace();
            Assert.fail();
        }
    }
    /**
     * @Description: sikuli 寻找图片默认时间
     * @param:
     * @return:
     */

    public void findImage(String imageName) throws IOException {
        try {
            screen.wait(this.sourcefile() + imageName).highlight(2);
            System.out.println("捕获到图片：" + imageName);
        } catch (FindFailed findFailed) {
            System.out.println("图片捕获失败");
            findFailed.printStackTrace();
            Assert.fail();
        }
    }
    /**
     * @Description: 寻找图片并进行点击
     * @param:
     * @return:
     */
    public void clickImage(String imageName) {
        try {
            screen.wait(this.sourcefile() + imageName).highlight(2).click();
//            screen.click(this.sourcefile() + imageName);
            System.out.println("点击图片：" + imageName);
        } catch (FindFailed findFailed) {
            System.out.println("未找到可点击的图片");
            findFailed.printStackTrace();
            Assert.fail();
        } catch (IOException e) {

        }
    }
    /**
     * @Description: 获取当前路径
     * @param:
     * @return:
     */
    public String sourcefile() throws IOException {
        File directory = new File(".");
        String sourceFile = directory.getCanonicalPath() + "/src/test/resources/screenImage/";
        return sourceFile;
    }
    /**
     * @Description: 获取当前窗口Handle
     * @param: []
     * @return: java.lang.String
     */
    public String befoWindowHandle() {
        String befo = driver.getWindowHandle();
        return befo;
    }
    /**
     * @Description: 获取当前所有窗口Handle并切换到下一个窗口
     * @param: []
     * @return: void
     */
    public void switchToWindow() {
        Set<String> after = driver.getWindowHandles();
        for (String afterHandle:after) {
            if (afterHandle.equals(this.befoWindowHandle())) {
                continue;
            }
            driver.switchTo().window(afterHandle);
        }
    }
    /**
     * @Description: 刷新页面
     * @param: []
     * @return: void
     */
    public void refresh() {
        driver.navigate().refresh();
    }
    /**
     * @Description: 页面回退
     * @param: []
     * @return: void
     */
    public void back() {
        driver.navigate().back();
    }
    /**
     * @Description: 拖动滚动条到页面底端
     * @param: []
     * @return: void
     */
    public void moveToBottom() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int i = 0; i<10; i++) {
            js.executeScript("window.scrollBy(0,document.body.scrollHeight || document.documentElement.scrollHeight)", "");
            System.out.println(i);
        }
    }
    /**
     * @Description: 拖动滚动条到页面顶部
     * @param: []
     * @return: void
     */
    public void moveToTop() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int i = 0; i<10; i++) {
            js.executeScript("window.scrollBy(0,-document.body.scrollHeight || -document.documentElement.scrollHeight)", "");
            System.out.println(i);
        }
    }
}
