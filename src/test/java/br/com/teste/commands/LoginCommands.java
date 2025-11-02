package br.com.teste.commands;

import br.com.teste.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginCommands {

    private WebDriver driver;
    private LoginPage page;
    private WebDriverWait wait;

    public LoginCommands(WebDriver driver) {
        this.driver = driver;
        this.page = new LoginPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarSistema(String url) {
        driver.get(url);
    }

    public void preencherUsuario(String usuario) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(page.campoUsuario));
        campo.clear();
        campo.sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(page.campoSenha));
        campo.clear();
        campo.sendKeys(senha);
    }

    public void clicarEntrar() {
        wait.until(ExpectedConditions.elementToBeClickable(page.botaoEntrar)).click();
    }
}
