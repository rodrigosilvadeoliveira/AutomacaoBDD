package br.com.teste.steps;

import br.com.teste.commands.LoginCommands;
import br.com.teste.support.DriverFactory;
import io.cucumber.java.pt.*;

import static org.junit.Assert.assertTrue;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginCommands login = new LoginCommands(driver);

    @Dado("que o usuário acesse o sistema")
    public void queOUsuarioAcesseOSistema() {
        login.acessarSistema();
    }

    @Quando("ele informar o usuário {string} e a senha {string}")
    public void informarUsuarioESenha(String usuario, String senha) {
        login.preencherUsuario(usuario);
        login.preencherSenha(senha);
    }

    @E("clicar em Entrar")
    public void clicarEmEntrar() {
        login.clicarEntrar();
    }

  @Então("o sistema deve exibir a tela inicial")
    public void sistemaDeveExibirTelaInicial() {
        assertTrue(login.verificarUrlInicial());
    }

    // @Então("o sistema deve exibir a tela acesso_negado")
    // public void sistemaDeveExibirTelaAcesso_negado() {
    //     assertTrue(login.verificarUrlNegado());
    // }

    @Dado("que estou na página de login")
    public void que_estou_na_pagina_de_login() {
        System.setProperty("webdriver.chrome.driver", "C://drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http:www.lirioMatriz.com.br/sistema"); // altere para sua URL real
    }

    @Quando("eu preencho o usuário e a senha incorretos")
    public void eu_preencho_o_usuario_e_a_senha_incorretos() {
        driver.findElement(By.id("login")).sendKeys("teste@teste.com");
        driver.findElement(By.id("senha")).sendKeys("senhaerrada");
    }

    @Quando("clico no botão de login")
    public void clico_no_botao_de_login() {
        login.clicarEntrar();
    }

    @Entao("o sistema deve exibir a mensagem de erro {string}")
    public void o_sistema_deve_exibir_a_mensagem(String mensagemEsperada) {
        login.validarMensagem(mensagemEsperada);
        driver.quit();
    }
}
