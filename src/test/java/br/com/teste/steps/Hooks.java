package br.com.teste.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import br.com.teste.support.DriverFactory;
import utils.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    private WebDriver driver;

    @Before
    public void beforeScenario() {
        driver = DriverFactory.getDriver();
        driver.get(Config.getBaseUrl());
        System.out.println("🚀 Iniciando cenário no ambiente: " + Config.getBaseUrl());
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                takeScreenshot(scenario, "FALHA");
            } else {
                takeScreenshot(scenario, "SUCESSO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DriverFactory.closeDriver();
            System.out.println("✅ Cenário finalizado. Navegador fechado.");
        }
    }

    private void takeScreenshot(Scenario scenario, String status) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

        // Nome do arquivo
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + "_" + status + "_" + timeStamp + ".png";

        // Caminho para salvar o print
        Path caminho = Paths.get("target", "screenshots", fileName);
        Files.createDirectories(caminho.getParent());
        Files.write(caminho, screenshot);

        // Também anexa ao relatório do Cucumber (Allure, Extent, etc.)
        scenario.attach(screenshot, "image/png", fileName);

        System.out.println("📸 Screenshot salvo em: " + caminho.toAbsolutePath());
    }
}