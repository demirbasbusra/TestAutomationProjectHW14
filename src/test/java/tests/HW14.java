package tests;

import drivers.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyManager;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.time.Duration;

public class HW14 {

    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager= new PropertyManager();
    String url =propertyManager.getProperty("APP_URL");

    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openDemoqaTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"DEMOQA");
    }

    @Test
    public void fillFormOnDemoqaTest(){
        //Buttons seçeneğine tıklatıyoruz
        WebElement elementButton= driver.findElement(new By.ByCssSelector("li#item-4"));
        elementButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        WebElement clickmeButton = driver.findElement(new By.ByCssSelector(".col-md-6 div:nth-of-type(3) > .btn"));
        clickmeButton.click();
        WebElement messageElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#dynamicClickMessage")));
        String message = messageElement.getText();
        System.out.println(message);
        Assert.assertEquals(message, "You have done a dynamic click");

        driver.get("https://demoqa.com/webtables");

        //Add butonuna tıklanır ve kullanıcı bilgileri girilir
        WebElement btnAdd = driver.findElement(new By.ByCssSelector("button#addNewRecordButton"));
        btnAdd.click();

        WebElement firstName = driver.findElement(new By.ByCssSelector("input#firstName"));
        firstName.sendKeys("Büşra");

        WebElement lastName = driver.findElement(new By.ByCssSelector("input#lastName"));
        lastName.sendKeys("Demirbaş");

        WebElement email = driver.findElement(new By.ByCssSelector("input#userEmail"));
        email.sendKeys("demirbas@gmail.com");

        WebElement id= driver.findElement(new By.ByCssSelector("input#age"));
        id.sendKeys("27");

        WebElement salary = driver.findElement(new By.ByCssSelector("input#salary"));
        salary.sendKeys("100000");

        WebElement department = driver.findElement(new By.ByCssSelector("input#department"));
        department.sendKeys("IT");

        WebElement btnSubmit = driver.findElement(new By.ByCssSelector("button#submit"));
        btnSubmit.click();

        //Düzenleme butonuna tıklandı
        WebElement btnEdit = driver.findElement(new By.ByCssSelector("div>div>span>svg>path"));
        btnEdit.click();

        WebElement firstName2 = driver.findElement(new By.ByCssSelector("input#firstName"));
        firstName2.sendKeys("Büşra2");

        WebElement btnSubmit2 = driver.findElement(new By.ByCssSelector("button#submit"));
        btnSubmit2.click();

    }

    @AfterMethod(alwaysRun = true)
    public void  after(){
        webDriver.quitDriver();
    }
}