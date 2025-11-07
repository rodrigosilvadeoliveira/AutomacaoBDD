package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe utilitária para gerar dados fake (nome, email, telefone etc.)
 * usando a API pública RandomUser.
 */
public class GeradorDadosFake {

    public static DadosFake gerarDados() throws IOException, InterruptedException {
        String apiUrl = Config.get("fake.api.url"); // já configurado no config.properties

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Falha ao acessar API de dados fake: " + response.statusCode());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        JsonNode user = root.path("results").get(0);

        // Extrai informações da API
        String first = user.path("name").path("first").asText("");
        String last  = user.path("name").path("last").asText("");
        String nomeCompleto = (first + " " + last).trim();

        String email = user.path("email").asText("");
        String telefone = user.path("phone").asText("");
        String celular = user.path("cell").asText("");
        String username = user.path("login").path("username").asText("");
        String senha = user.path("login").path("password").asText("");

        // Retorna o objeto contendo todos os dados
        return new DadosFake(nomeCompleto, email, telefone, celular, username, senha);
    }
}
