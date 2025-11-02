#language: pt
Funcionalidade: Login no sistema

  Cenário: Acessar o site e fazer login com sucesso
    Dado que o usuário acesse o sistema
    Quando ele informar o usuário "master" e a senha "@lirio2025"
    E clicar em Entrar
    Então o sistema deve exibir a tela inicial
