package br.com.teste.commands;

import br.com.teste.pages.LoginPage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import javax.print.DocFlavor.STRING;

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
    public boolean verificarUrlInicial() {
        wait.until(ExpectedConditions.elementToBeClickable(page.botaoFecharBanner)).click();
        return driver.getCurrentUrl().equals("https://www.liriomatriz.com.br/paginainicial");
    }

    public boolean verificarUrlNegado() {
        return driver.getCurrentUrl().equals("https://www.liriomatriz.com.br/acesso_negado.php");
    }

    public void validarMensagem(String mensagemEsperada) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Aguarda até que o elemento tenha texto visível
    WebElement mensagem = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.id("formlogin"))
    );

    String textoObtido = mensagem.getText().trim(); // remove espaços extras
    System.out.println("Texto encontrado: " + textoObtido);

    Assert.assertEquals("Mensagem incorreta exibida!", mensagemEsperada, textoObtido);
        System.out.println(textoObtido);
    }

    
}
