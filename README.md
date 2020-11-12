# 自动化测试规范文档
本文描述自动化只用框架以及规范
* 项目结构
```
├─main
│  ├─java
│  └─resources
└─test
    ├─java
    │  ├─page
    │  ├─runner
    │  └─stepdefs
    └─resources
        ├─browserdriver
        ├─exceldata
        ├─features
        ├─locator
        └─screenImage
```
### 结构解析
* 所有脚本都将在test目录下进行开发

|目录|解释|
|---|---|
|page-->|封装一个模块或者功能的页面操作|
|runner-->|用例执行器，主要使用@CucumberOptions注解配置报告输出以及用例的调度，Tag的使用请查阅第一部分[cucumber](http://pan.cdecube.com/d/364d704142bc491fa6dd/)的配置|
|stepdefs-->|所有case将在此目录下开发|
|browserdriver-->|存放各浏览器的驱动，并定期更新|
|exceldata-->|存放数据驱动的文档数据|
|features-->|存放行为驱动用例，features的使用请查阅第一部分[cucumber](http://pan.cdecube.com/d/364d704142bc491fa6dd/)的配置|
|locator-->|存放所有脚本的元素不可以在业务代码中出现定位元素|
|screenImage-->|存放所有异常截图|

### 引入seleniumapi
* seleniumapi采用数据驱动、分层结构、元素分离，高内聚低耦合的设计模式并封装了各类通用的方法，是目前自动化框架的核心部件。
将gitlab上源码拉下来或者，下载jar包，在maven中引入本地jar包
* 项目地址
```
git@git.cdecube.com:test/seleniumapi.git
```
* 目录结构
```
├─main
│  ├─java
│  │  └─com
│  │      └─cdecube
│  │          └─agency
│  │              └─common
│  └─resources
└─test
    ├─java
    └─resources
        ├─browserdriver
        ├─exceldata
        ├─locator
        └─screenImage
```
### Class类分析
|目录|解释|
|---|---|
|BaseTest|case基类，所有case继承该类，根据场景不同可以重写beforclass，afterclass方法|
|DotTestListener|监听类，自动监听Assert断言，如果失败自动截图，可在xml中配置全局监听|
|ExcelData|解析excel|
|Log|log4j|
|Page|解析yaml并封装Webdriver所有方法，以及其他第三方方法|
|Preset|前置条件类，构造driver|
|ScreenShot|封装截图方法|
|selectDriver|driver工厂，所有driver必须出自于此，否则会空指针|
|tool|常用工具类|

* 主要方法解析

>[com.cdecube.agency.common](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common)

>>[DotTestListener](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/DotTestListener.java "监听类")<br>

>>>[onTestFailuregit](# "-->*重写onTestFailure方法如果用例执行失败，就调用takeScreenshot截图*")<br>

>>[ExcelData](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/ExcelData.java "读取Excle方法（数据驱动）") <br>
>>[Page](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/Page.java "通用API封装类")<br>

>>>[getYamFile](# "-->*读取yaml文件*")<br>
>>>[getBy](# "-->*枚举各by方法*")<br>
>>>[watiForElement](# "-->*显示等待方法*")<br>
>>>[getElement](# "-->*获取单个元素方法*")<br>
>>>[getElements](# "-->*获取一组元素方法*")<br>
>>>[waitgetElement](# "-->*获取单个元素等待方法（调用显示等待方法，可自由传入需要等待的时间）*")<br>
>>>[areaGetElements](# "-->*获取固定区域下的一组元素*")<br>

>>>[addCookies](# "-->*封装addCookie方法，绕过复杂验证码登录*")<br>
>>>[moveElement](# "-->*封装action动作,模拟鼠标移动到元素上*")<br>
>>>[getUrl](# "-->*获取Url*")<br>
>>>[isDisplay](# "-->*判断元素是否存在*")<br>
>>>[sleep](# "-->*封装Thread.sleep方法*")<br>

>>[Preset](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/Preset.java "前置条件封装类")<br>

>>>[getDriver](http://git.test.cdecube.com/liupeng/agencyEpc "获取driver的静态方法")<br>
>>>[timeout](# "隐式等待方法")<br>
>>>[maxwind](# "窗口最大化")<br>
>>>[quit](# "退出浏览器")<br>


>>[ScreenShot](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/ScreenShot.java "封装截图方法")<br>
>>[selectDriver](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/selectDriver.java "Driver选择器封装类")<br>
>>[tool](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/main/java/com/cdecube/agency/common/tool.java "常用工具封装类")<br>

>[com.cdecube.agency](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/test/java/com/cdecube/agency)
>>[action](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/test/java/com/cdecube/agency/action "对一些用例进行封装") <br>
>>[po](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/test/java/com/cdecube/agency/po "po模式") <br>
>>[test](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/test/java/com/cdecube/agency/test "作为调试的地方") <br>
>>[testcase](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/test/java/com/cdecube/agency/testcase "存放用例的地方") <br>

>[resources](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/test/resources "allure配置文件") <br>

>[pom.xml](# "pom配置文件")<br>
>[testng.xml](# "case配置文件")<br>
>[locator](http://git.test.cdecube.com/liupeng/agencyEpc/tree/develop/src/locator "yaml文件")<br>
