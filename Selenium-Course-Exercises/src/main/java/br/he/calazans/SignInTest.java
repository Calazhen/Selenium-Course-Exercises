package br.he.calazans;

import DSL.DSL;
import Pages.TrainingGroundPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignInTest {
    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";
    private WebDriver driver;
    private DSL dsl;
    private TrainingGroundPage page;

    @Before
    public void inicialize (){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new TrainingGroundPage(driver);

    }
    @After
    public void killingDriver (){
        driver.quit();
    }

    @Test
    public void fillingFullForms (){
     //   dsl.writing("elementosForm:nome","Henrique");
        page.setName("Henrique");
        dsl.writing("elementosForm:sobrenome","Calazans");
        dsl.clicckOnRadioButton("elementosForm:sexo:0");
        dsl.clickOnButton("elementosForm:comidaFavorita:1");
        dsl.selectCombo("ElementosForm:escolaridade","Superior");
        dsl.selectCombo("ElementosForm:esportes","Futebol");
        dsl.selectCombo("ElementosForm:esportes","Corrida");
        dsl.unselectCombo("ElementosForm:esportes","Corrida");
        dsl.clickOnButton("elementosForm:cadastrar");


        Assert.assertTrue(dsl.getText("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.getText("descNome").endsWith("Henrique"));
        Assert.assertEquals("Sobrenome: Calazans",dsl.getText("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino",dsl.getText("descSexo"));
        Assert.assertEquals("Comida: Frango",dsl.getText("descComida"));
        Assert.assertEquals("Escolaridade: superior",dsl.getText("descEscolaridade"));
        Assert.assertEquals("Esportes: Futebol",dsl.getText("descEsportes"));
        Assert.assertEquals("Sugestoes:",dsl.getText("descSugestoes"));
    }
}
