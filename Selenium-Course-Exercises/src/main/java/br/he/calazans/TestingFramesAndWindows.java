package br.he.calazans;

import DSL.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestingFramesAndWindows {
    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";

    private WebDriver driver;
    private DSL dsl;


    @Before
    public void inicialize (){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
    }
    @After
    public void killingDriver (){
        driver.quit();
    }


    @Test
    public void interactingFrames() {
        dsl.switchToFrame("frame1");
        dsl.clickOnButton("frameButton");

        String msg = dsl.getTextAndAccept();
        Assert.assertEquals("Frame OK!", msg);

        dsl.leftFrame();
        dsl.writing("elementosForm:nome",msg);
    }

    @Test
    public void interactingWindows() {
        String winHandBefore = driver.getWindowHandle();
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");

        driver.close();

        driver.switchTo().window(winHandBefore);

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("e agora?");
    }

    @Test
    public void usingWindowHandler() {
        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }



}

