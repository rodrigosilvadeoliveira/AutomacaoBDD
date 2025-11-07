package br.com.teste.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    
    public By campoUsuario = By.id("login");
    public By campoSenha = By.id("senha");
    public By botaoEntrar = By.cssSelector("input[type='submit'][value='Entrar']");
    public By botaoFecharBanner = By.xpath("//button[@class='close-banner']");
    private By mensagemErro = By.id("formlogin");
    public String obterMensagemErro() {
        WebElement elemento = driver.findElement(mensagemErro);
        return elemento.getText();
    }

    public By togleMenu = By.id("menuToggle");
    public By menuAdministracao = By.xpath("//div[@class='mobile-category-title' and normalize-space(text())='Administração']");
    public By menuCadastroAcesso = By.xpath("//div[@class='mobile-submenu']//a[normalize-space(text())='Cadastro Acesso']");
    public By botaoSalvarCadastro = By.id("submitAdm");
}
