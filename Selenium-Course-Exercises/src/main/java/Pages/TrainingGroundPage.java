package Pages;

import DSL.DSL;
import org.openqa.selenium.WebDriver;

public class TrainingGroundPage {
    private DSL dsl;

    public TrainingGroundPage(WebDriver driver){
        dsl = new DSL(driver);
    }
    public void setName(String nome){
        dsl.writing ("elementosForm:nome", nome);

    }
}
