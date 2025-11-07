#language: pt
Funcionalidade: Login no sistema

@loginSucesso
  Cenário: 01.Acessar o site e fazer login com sucesso
    Dado que o usuário acesse o sistema
    Quando ele informar o usuário "master" e a senha "@lirio2025"
    E clicar em Entrar
    Então o sistema deve exibir a tela inicial
@loginInvalido
 Cenário: 02.Exibir mensagem de erro ao inserir credenciais inválidas
    Dado que o usuário acesse o sistema
    Quando eu preencho o usuário e a senha incorretos
    E clico no botão de login
    Então o sistema deve exibir a mensagem de erro "Usuário ou senha inválida"
 
 @cadastroUsuario
 Cenário: 03.Cadastrar acesso
    Dado que acesso com usuário "master" e a senha "@lirio2025"
    Quando acessar a tela de cadastro de Acesso
    E preencho dados para cadastro com perfil Consulta
    Então cadastro realizado com sucesso