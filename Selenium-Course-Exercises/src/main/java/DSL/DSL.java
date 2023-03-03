package DSL;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


public class DSL {
    private WebDriver driver;


    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    /******** TextField and TextArea ********/

    public void writing(By by, String texto) {

        driver.findElement(by).sendKeys(texto);
    }

    public void writing(String id_field, String text) {
        driver.findElement(By.id(id_field)).clear();
        driver.findElement(By.id(id_field)).sendKeys(text);

    }

    public String getFieldValue(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    /******** Radio and checkbox ********/
    public void clicckOnRadioButton(String id) {
        driver.findElement(By.id(id)).click();
    }

    public Boolean isRadioButtonMarked(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    public void clicckOnCheckBox(String id) {
        driver.findElement(By.id(id)).click();
    }

    public Boolean isCheckBoxMarked(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    /******** Combo ********/
    public void selectCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void unselectCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public String getComboValue(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> getAllComboValues(String id) {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> values = new ArrayList<String>();
        for(WebElement opcao: allSelectedOptions) {
            values.add(opcao.getText());
        }
        return values;
    }
    public int getQuantityOfOptionsInCombo(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verifyingAnSpecificOptionInCombo(String id, String optionInCombo) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for(WebElement option: options) {
            if(option.getText().equals(optionInCombo)){
                return true;
            }
        }
        return false;
    }

    /******** Button ********/
    public void clickOnButton(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String getButtonValue(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    /******** Links ********/
    public void clickOnLink(String id) {
        driver.findElement(By.linkText(id)).click();
    }

    /******** Texts ********/
    public String getText(By by) {
        return driver.findElement(by).getText();
    }

    public String getText(String id) {
        return getText(By.id(id));
    }

    public void clickOnAlert(String id) {
        driver.findElement(By.id(id)).click();
    }

    /******** Alerts ********/
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public String getTextAndAccept() {
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;
    }

    public String getTextAndDismiss() {
        Alert alert = driver.switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;
    }

    public void writtingOnAlert(String value) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(value);
        alert.accept();
    }

    /******** Frames and Windows ********/

    public void switchToFrame(String id) {
        driver.switchTo().frame(id);
    }

    public void leftFrame() {
        driver.switchTo().defaultContent();
    }

    public void changeWindow(String id) {
        driver.switchTo().window(id);
    }


}
