package br.he.calazans;

import DSL.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestingAlerts {
    String driverPath ="C:\\Users\\calaz\\chromedriver.exe";
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicialize (){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void killingDriver (){
        driver.quit();
    }

    @Test
    public void interactingAlerts (){
        dsl.clickOnAlert("alert");
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        Assert.assertEquals("Alert Simples",textAlert);
        alert.accept();

        dsl.writing("elementosForm:nome",textAlert);
    }
    @Test
    public void interactingAcceptingAlerts (){
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples",alert.getText());
        alert.accept();
        alert.accept();
    }
    @Test
    public void interactingAlertPrompt (){
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero",alert.getText());
        alert.sendKeys("123456789");
        alert.accept();
        Assert.assertEquals("Era 123456789?",alert.getText());
        alert.accept();
        Assert.assertEquals(":D",alert.getText());
        alert.accept();
    }

}
