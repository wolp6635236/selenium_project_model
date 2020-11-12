package com.cdecube.common;

import jxl.read.biff.BiffException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

/**
 * @Author: liupeng
 * @Description:
 * @Date: Created in 16:41 2018/8/7
 * @Modified By:
 */
public class BaseTest {
    public Preset preset;
    public Page page;
    public static Logger log = LoggerFactory.getLogger(BaseTest.class);

    @DataProvider(name = "num")
    public Object[][] Numbers() throws BiffException, IOException {
        ExcelData e = new ExcelData("testdatas2", "calculator");
        return e.getExcelData();
    }
    @BeforeClass
    @Parameters({"driverClass"})
    public void beforeClass(String driverClass) {
        log.info("----------------------用例开始，打开浏览器-----------------------");
        preset = new Preset(driverClass);
        preset.maxwind();
        preset.timeout(5);
        page = new Page(preset.getDriver());
    }
    @AfterClass
    public void afterClass() {
        try {
            page.sleep(1000);
        } catch (InterruptedException e) {
        }
        log.info("----------------------用例结束，退出浏览器-----------------------");
        preset.quit();
    }

}