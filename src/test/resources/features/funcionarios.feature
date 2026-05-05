# language: pt
Funcionalidade: Gerenciamento de Funcionários - Pilar Social ESG
  Como um gestor de RH focado em diversidade
  Quero cadastrar e consultar funcionários
  Para acompanhar as métricas de inclusão da empresa

  Cenário: Cadastro de um novo funcionário PCD com sucesso (Caminho Feliz)
    Dado que eu tenho os dados de um novo funcionário chamado "Mariana Alves"
    E ela é uma funcionária "PCD" com necessidade de "Ledor de tela"
    Quando eu envio uma requisição POST para "/api/funcionarios"
    Então o status code da resposta deve ser 201
    E o corpo da resposta deve conter o id gerado
    E o "nomeRegistro" no corpo da resposta deve ser "Mariana Alves"

  Cenário: Falha ao cadastrar funcionário com payload inválido (Caminho Negativo)
    Dado que eu envio um payload vazio ou malformado
    Quando eu envio uma requisição POST para "/api/funcionarios"
    Então o status code da resposta deve ser 400 ou 500
    E a resposta não deve gerar um novo cadastro

  Cenário: Consulta de listagem de funcionários PCDs (Validação de API e Contrato)
    Dado que existem funcionários PCDs cadastrados no banco de dados
    Quando eu envio uma requisição GET para "/api/funcionarios/pcd"
    Então o status code da resposta deve ser 200
    E a resposta deve ser uma lista não vazia
    E o contrato da resposta deve respeitar o schema "funcionarios-schema.json"