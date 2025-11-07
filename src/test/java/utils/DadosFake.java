package utils;

/**
 * Representa um conjunto de dados fake retornados pela API RandomUser.
 */
public class DadosFake {
    private String nomeCompleto;
    private String email;
    private String telefone;
    private String celular;
    private String username;
    private String senha;

    public DadosFake(String nomeCompleto, String email, String telefone, String celular, String username, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.username = username;
        this.senha = senha;
    }

    // Getters
    public String getNomeCompleto() { return nomeCompleto; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCelular() { return celular; }
    public String getUsername() { return username; }
    public String getSenha() { return senha; }

    @Override
    public String toString() {
        return String.format("Nome: %s | Email: %s | Celular: %s | Usuário: %s", nomeCompleto, email, celular, username);
    }
}
