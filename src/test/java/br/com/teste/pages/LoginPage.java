package br.com.teste.pages;

import org.openqa.selenium.By;

public class LoginPage {

    public By campoUsuario = By.id("login");
    public By campoSenha = By.id("senha");
    public By botaoEntrar = By.cssSelector("input[type='submit'][value='Entrar']");
}
