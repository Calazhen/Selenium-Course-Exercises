package br.he.calazans;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class TrainingGroundTests {
    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";
    String toBeWrittenInTextArea = "\n Henrique\nC.\nG.\nBaptista";
    String expectedName = "Henrique C. G. Baptista";

    private WebDriver driver;
    private DSL dsl;


    @Before
    public void inicialize (){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir")+"/src/main/resources/componentes.html");
        dsl= new DSL(driver);
    }

    @After
    public void killingDriver (){
        driver.quit();
    }

// Testando e Validando Título da Página//
    @Test
    public void gettingTitle(){
      String tituloCampoTreinamentoAtual = driver.getTitle();
      Assert.assertEquals("Campo de Treinamento",tituloCampoTreinamentoAtual);
}

// Testando e validando texto escrito //
    @Test
    public void interactingTextField() {
        dsl.writing("elementosForm:nome", "Henrique C. G. Baptista");
        Assert.assertEquals(expectedName, dsl.getFieldValue("elementosForm:nome"));
    }

// Testando Text area, a diferença é que eu posso escrever várias linhas
    @Test
    public void interactingTextArea(){
        dsl.writing("elementosForm:sugestoes", "\"\\n Henrique\\nC.\\nG.\\nBaptista\"");
        Assert.assertEquals("\"\\n Henrique\\nC.\\nG.\\nBaptista\"",dsl.getFieldValue("elementosForm:sugestoes"));

    }
    @Test
    public void interactingRadioButton (){
        dsl.clicckOnRadioButton("ElementosForm:sexo:0");
        Assert.assertTrue(dsl.radioButtonIsMarked("ElementosForm:sexo:0"));
    }
    @Test
    public void interactingCheckBox (){
        dsl.clicckOnRadioButton("ElementosForm:comidaFavorita:1");
        Assert.assertTrue(dsl.radioButtonIsMarked("ElementosForm:comidaFavorita:1"));
    }
    @Test
    public void interactingCombo (){
        dsl.selectCombo("elementosForm:escolaridade","2o grau incompleto");
        Assert.assertEquals("2o grau incompleto",dsl.getComboValue("elementosForm:escolaridade"));
    }
    @Test
    public void interactingMultipleChoisesCombo(){
        dsl.selectCombo("elementosForm:esportes","Natacao");
        dsl.selectCombo("elementosForm:esportes","Corrida");
        dsl.selectCombo("elementosForm:esportes","O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select (element);

        //Selecionando 3 opções em uma lista com Scrollbar
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3,allSelectedOptions.size());

        // Retirando a seleção:
        combo.deselectByVisibleText("Corrida");
    }
    @Test
    public void interactingButtons () {
        dsl.clickOnButton("buttonSimple");
        Assert.assertEquals("Obrigado!",dsl.getButtonValue("buttonSimple"));
    }
    @Test
    public void interactingLinks (){
        dsl.clickOnLink("Voltar");
    Assert.assertEquals("Voltou!", dsl.getText("resultado"));
    }
    @Test
    public  void seachingTextsOnPage(){
        /* Para buscar o todos os textos da página e verficiar se contém o valor "Campo de Treinamento".
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));   */

         //  Para buscar o primeiro elemento de uma tag de uma página.
         Assert.assertEquals("Campo de Treinamento",dsl.getText(By.tagName("h3")));


         // Para buscar uma elemento pelo nome da classe, ou seja, algo único que o referêncie na página.
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",dsl.getText(By.className("facilAchar")));
    }

}