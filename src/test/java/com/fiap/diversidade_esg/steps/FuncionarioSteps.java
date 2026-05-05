package com.fiap.diversidade_esg.steps;

import io.cucumber.java.pt.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class FuncionarioSteps {

    private String baseUri = "http://localhost:8080";
    private String payload;
    private Response response;

    @Dado("que eu tenho os dados de um novo funcionário chamado {string}")
    public void criarPayloadValido(String nome) {
        payload = "{ \"id\": 999, \"nomeRegistro\": \"" + nome + "\", \"cargo\": \"Analista\", \"pcd\": true }";
    }

    @Dado("ela é uma funcionária {string} com necessidade de {string}")
    public void adicionarNecessidadePcd(String pcd, String necessidade) {
    }

    @Quando("eu envio uma requisição POST para {string}")
    public void enviarPost(String endpoint) {
        response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(baseUri + endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void validarStatusCode(int statusCodeEsperado) {
        response.then().statusCode(statusCodeEsperado);
    }

    @Então("o corpo da resposta deve conter o id gerado")
    public void validarIdGerado() {
        response.then().body("id", notNullValue());
    }

    @Então("o {string} no corpo da resposta deve ser {string}")
    public void validarCampoResposta(String campo, String valorEsperado) {
        response.then().body(campo, equalTo(valorEsperado));
    }

    @Dado("que eu envio um payload vazio ou malformado")
    public void criarPayloadInvalido() {
        payload = "{ \"nomeInvalido\": \"Teste\" ";
    }

    @Então("a resposta não deve gerar um novo cadastro")
    public void validarFalhaCadastro() {
        response.then().body("error", notNullValue());
    }

    @Dado("que existem funcionários PCDs cadastrados no banco de dados")
    public void prepararBanco() {
    }

    @Quando("eu envio uma requisição GET para {string}")
    public void enviarGet(String endpoint) {
        response = given().when().get(baseUri + endpoint);
    }

    @Então("a resposta deve ser uma lista não vazia")
    public void validarLista() {
        response.then().body("size()", greaterThan(0));
    }

    @Então("o contrato da resposta deve respeitar o schema {string}")
    public void validarContrato(String schemaFile) {
        response.then().body(matchesJsonSchemaInClasspath("schemas/" + schemaFile));
    }

    @Então("o status code da resposta deve ser {int} ou {int}")
    public void validarStatusCodeMultiplo(int status1, int status2) {
        response.then().statusCode(anyOf(is(status1), is(status2)));
    }
}