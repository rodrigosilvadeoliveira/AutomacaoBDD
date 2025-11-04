#language: pt
Funcionalidade: Login no sistema

@loginSucesso
  Cenário: Acessar o site e fazer login com sucesso
    Dado que o usuário acesse o sistema
    Quando ele informar o usuário "master" e a senha "@lirio2025"
    E clicar em Entrar
    Então o sistema deve exibir a tela inicial
@loginInvalido
 Cenário: Exibir mensagem de erro ao inserir credenciais inválidas
    Dado que o usuário acesse o sistema
    Quando eu preencho o usuário e a senha incorretos
    E clico no botão de login
    Então o sistema deve exibir a mensagem de erro "Usuário ou senha inválida"
 