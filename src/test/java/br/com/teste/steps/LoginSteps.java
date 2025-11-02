package br.com.teste.steps;

import br.com.teste.commands.LoginCommands;
import br.com.teste.support.DriverFactory;
import io.cucumber.java.pt.*;

import org.openqa.selenium.WebDriver;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginCommands login = new LoginCommands(driver);

    @Dado("que o usuário acesse o sistema")
    public void queOUsuarioAcesseOSistema() {
        login.acessarSistema("https://www.liriomatriz.com.br/sistema");
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
    public void validarLogin() {
        // Aqui você pode validar algum elemento da tela inicial
    }
}
