package br.com.teste.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import br.com.teste.support.DriverFactory;
import utils.Config;

public class Hooks {

    private WebDriver driver;

    @Before
    public void beforeScenario() {
        driver = DriverFactory.getDriver();
        driver.get(Config.getBaseUrl());
        System.out.println("🚀 Iniciando cenário no ambiente: " + Config.getBaseUrl());
    }

    @After
    public void afterScenario() {
        DriverFactory.closeDriver();
        System.out.println("✅ Cenário finalizado. Navegador fechado.");
    }
}