package br.he.calazans;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DSL {
    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }


    public void writing(String id_field, String text) {
        driver.findElement(By.id(id_field)).sendKeys(text);

    }

    public String getFieldValue(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    public void clicckOnRadioButton(String id) {
        driver.findElement(By.id(id)).click();
    }

    public Boolean radioButtonIsMarked(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    public void selectCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String getComboValue(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clickOnButton(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String getButtonValue(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    public void clickOnLink(String id) {
        driver.findElement(By.linkText(id)).click();
    }

    public String getText (By by){
        return driver.findElement(by) .getText();
    }
    public String getText (String id){
        return getText(By.id(id));
    }

}
