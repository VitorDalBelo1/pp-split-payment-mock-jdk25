package br.gov.pp.splitpayment;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class MockEndpointsTest {

    @Test
    void informeTransacaoIniciadaBoleto() {
        given().contentType("application/json").body("{}")
                .when().post("/api/v1/boleto")
                .then().statusCode(201)
                .body("status", is(201))
                .header("X-JWS-Signature", notNullValue());
    }

    @Test
    void informePreliminarTed() {
        given().contentType("application/json").body("{}")
                .when().post("/api/v1/ted/informe-preliminar-pagamento")
                .then().statusCode(201);
    }

    @Test
    void streamStartDevolveTokens() {
        given().when().get("/api/v1/out/pix-dinamico/12345678/transacoes/stream/start")
                .then().statusCode(200)
                .header("streamId", notNullValue())
                .header("proximoToken", notNullValue())
                .body("transacoes.size()", is(1));
    }

    @Test
    void streamDeleteDevolve204() {
        given().when().delete("/api/v1/out/pix-dinamico/12345678/transacoes/stream/abc123")
                .then().statusCode(204);
    }

    @Test
    void loteSegregacao() {
        given().contentType("application/json").body("{}")
                .when().post("/api/v1/tef/segregacao/ABC123DEF456GHI7PXD202604231930001/lotes")
                .then().statusCode(201);
    }

    @Test
    void consultaMoc() {
        given().queryParam("nsuInicial", "1")
                .when().get("/api/v1/moc/12345678/ocorrencias")
                .then().statusCode(200)
                .body("ocorrencias", notNullValue());
    }

    @Test
    void jwksPublicaChavesDeAssinaturaETls() {
        given().when().get("/.well-known/jwks.json")
                .then().statusCode(200)
                .header("Cache-Control", containsString("max-age"))
                .body("keys.use", hasItems("sig", "tls"))
                .body("keys.kid", hasItems("2026-06-key-1", "2026-06-mtls-1"))
                .body("keys.find { it.use == 'sig' }.alg", is("RS256"))
                .body("keys.find { it.use == 'sig' }.n", notNullValue())
                .body("keys.find { it.use == 'sig' }.\"x5t#S256\"", notNullValue())
                .body("keys.find { it.use == 'tls' }.x5c.size()", is(1));
    }

    @Test
    void openApiPublicado() {
        given().when().get("/openapi")
                .then().statusCode(200);
    }
}
