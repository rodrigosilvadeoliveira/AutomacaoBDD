package br.com.teste.commands;

import br.com.teste.pages.LoginPage;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.DadosFake;
import utils.GeradorDadosFake;
import static org.junit.Assert.assertEquals;

import java.time.Duration;

import javax.print.DocFlavor.STRING;

public class LoginCommands {

    private WebDriver driver;
    private LoginPage page;
    private WebDriverWait wait;
    private static DadosFake ultimoDadoGerado; // guarda os dados da última execução
    
    public LoginCommands(WebDriver driver) {
        this.driver = driver;
        this.page = new LoginPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarSistema() {
        driver.get(Config.getBaseUrl());
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
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    try {
        // Localiza o elemento (ex: botão "Fechar")
        WebElement botaoFechar = driver.findElement(page.botaoFecharBanner);

        // Verifica se está visível e clicável
        if (botaoFechar.isDisplayed() && botaoFechar.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(botaoFechar)).click();
            System.out.println("✅ Banner fechado com sucesso.");
        } else {
            System.out.println("ℹ️ Elemento encontrado, mas não está visível ou clicável.");
        }

    } catch (org.openqa.selenium.NoSuchElementException e) {
        // Elemento não existe na tela — não faz nada
        System.out.println("ℹ️ Banner não apareceu, seguindo o fluxo normal...");
    } catch (Exception e) {
        // Caso aconteça outro erro inesperado
        System.out.println("⚠️ Erro ao tentar fechar o banner: " + e.getMessage());
    }
        return driver.getCurrentUrl().contains("/paginainicial");
    }

    public boolean verificarUrlNegado() {
        return driver.getCurrentUrl().equals("/acesso_negado.php");
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

    public void clickMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(page.togleMenu)).click();
    }

     public void telaCadastroAcesso() {
        wait.until(ExpectedConditions.elementToBeClickable(page.menuAdministracao)).click();
         wait.until(ExpectedConditions.elementToBeClickable(page.menuCadastroAcesso)).click();
    }

    public void preencherFormularioComDadosFakes() {
        try {
            // 1️⃣ Gera todos os dados via API
            DadosFake dados = GeradorDadosFake.gerarDados();
            ultimoDadoGerado = dados; // armazena para usar em outras etapas (validação, logs, etc.)

            // 2️⃣ Preenche o formulário
            driver.findElement(By.id("nome")).sendKeys(dados.getNomeCompleto());
            driver.findElement(By.id("email")).sendKeys(dados.getEmail());
            driver.findElement(By.id("telefone")).sendKeys(dados.getTelefone());
            driver.findElement(By.id("celular")).sendKeys(dados.getCelular());
            driver.findElement(By.id("usuario")).sendKeys(dados.getUsername());
            driver.findElement(By.id("senha")).sendKeys(dados.getSenha());

            System.out.println("✅ Dados fake gerados e preenchidos:");
            System.out.println(dados);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar e preencher dados fake: " + e.getMessage(), e);
        }
    }

    // Getter para acessar os dados do último teste
    public static DadosFake getUltimoDadoGerado() {
        return ultimoDadoGerado;
    }

    public void selecionarNivelAcessoConsulta() {
        WebElement selectElement = driver.findElement(By.id("nivel_acesso"));

        // Cria o objeto Select
        Select select = new Select(selectElement);

        // 3 opções possíveis (você pode escolher uma delas conforme o que for mais estável)
        select.selectByVisibleText("Consulta"); // ✅ pelo texto visível
        // select.selectByValue("consulta");    // ✅ pelo valor do atributo "value"
        // select.selectByIndex(2);             // (opcional) pelo índice (0 = primeiro)

        System.out.println("✅ Opção 'Consulta' selecionada com sucesso!");
    }

     public void clickSalvarCadastro() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(page.botaoSalvarCadastro)).click();
        //Thread.sleep(15000);
    }

    public String capturarTextoConfirmacao() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String mensagem = alert.getText(); // ✅ Captura o texto do alerta
        alert.accept(); // Clica no "OK"
        return mensagem;
    }

}
