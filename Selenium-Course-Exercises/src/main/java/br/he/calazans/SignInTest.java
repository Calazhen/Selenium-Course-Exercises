package br.he.calazans;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class SignInTest {
    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";
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
    public void fillingFullForms (){
        dsl.writing("elementosForm:nome","Henrique");
        dsl.writing("elementosForm:sobrenome","Calazans");
        dsl.clicckOnRadioButton("elementosForm:sexo:0");
        dsl.clickOnButton("elementosForm:comidaFavorita:1");
        dsl.selectCombo("ElementosForm:escolaridade","Superior");
        dsl.selectCombo("ElementosForm:esportes","Futebol");
        dsl.clickOnButton("elementosForm:cadastrar");


        Assert.assertTrue(dsl.getText("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.getText("descNome").endsWith("Henrique"));
        Assert.assertEquals("Sobrenome: Calazans",dsl.getText("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino",dsl.getText("descSexo"));
        Assert.assertEquals("Comida: Frango",dsl.getText("descComida"));
        Assert.assertEquals("Escolaridade: superior",dsl.getText("descEscolaridade"));
        Assert.assertEquals("Sugestoes:",dsl.getText("descSugestoes"));
    }
}
