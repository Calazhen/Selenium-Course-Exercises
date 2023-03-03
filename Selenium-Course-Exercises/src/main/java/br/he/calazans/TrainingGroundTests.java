package br.he.calazans;

import DSL.DSL;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class TrainingGroundTests {
    String driverPath = "C:\\Users\\calaz\\chromedriver.exe";
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

//  Testando se os Nomes estao sendo escritos corretamente //
    @Test
    public void  validateWrittingOnName_sField (){
        dsl.writing("elementosForm:nome","Henrique");
        Assert.assertEquals("Henrique", dsl.getFieldValue("elementosForm:nome"));
        dsl.writing("elementosForm:nome","Wagner");
        Assert.assertEquals("Wagner", dsl.getFieldValue("elementosForm:nome"));
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
        Assert.assertTrue(dsl.isRadioButtonMarked("ElementosForm:sexo:0"));
    }
    @Test
    public void interactingCheckBox (){
        dsl.clicckOnCheckBox("ElementosForm:comidaFavorita:1");
        Assert.assertTrue(dsl.isCheckBoxMarked("ElementosForm:comidaFavorita:1"));
    }
    @Test
    public void interactingCombo () {
        dsl.selectCombo("elementosForm:escolaridade", "2o grau incompleto");
        Assert.assertEquals("2o grau incompleto", dsl.getComboValue("elementosForm:escolaridade"));
    }

    @Test
    public void getQuantityOfOptionsInCombo(){

        // Verifica quantidade de opções existentes no combo
        Assert.assertEquals(8,dsl.getQuantityOfOptionsInCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verifyingAnSpecificOptionInCombo("elementosForm:escolaridade","Mestrado"));
    }


    @Test
    public void interactingMultipleValuesInCombo(){

        //Selecionando 3 opções numa lista com Scrollbar
        dsl.selectCombo("elementosForm:esportes","Natacao");
        dsl.selectCombo("elementosForm:esportes","Corrida");
        dsl.selectCombo("elementosForm:esportes","O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select (element);

        List<String> selectedOptions = dsl.getAllComboValues("elementosForm:esportes");
        Assert.assertEquals(3,selectedOptions.size());

        // Retira a opção "Corrida" e verifica novamento os valores selecionados
        dsl.unselectCombo("elementosForm:esportes","Corrida");
        selectedOptions = dsl.getAllComboValues("elementosForm:esportes");
        Assert.assertEquals(2,selectedOptions.size());

        //Verifica valores selecionados pelo combo
        dsl.getAllComboValues("elementosForm:esportes").toString().contains("[Natacao, Corrida, O que eh esporte?]");

        // Mesma verificação de cima, porém aplicado corretamente sem converter para String:
        Assert.assertTrue(selectedOptions.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

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


         // Para buscar um elemento pelo nome da classe, ou seja, algo único que o referêncie na página.
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",dsl.getText(By.className("facilAchar")));
    }

}