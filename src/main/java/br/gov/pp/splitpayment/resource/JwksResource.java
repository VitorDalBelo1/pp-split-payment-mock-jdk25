package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockStore;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Endpoint JWKS da Plataforma Publica (RFC 7517).
 *
 * Conforme o Manual de Seguranca (secao 5.4), publica as chaves publicas da PP:
 *  - use "sig": validacao das assinaturas JWS (X-JWS-Signature);
 *  - use "tls": material de autenticacao mTLS.
 * Cada chave expoe kid, alg, use, validade e fingerprint (x5t#S256).
 *
 * O endereco oficial ainda esta "a definir" no manual; aqui usa-se o caminho
 * convencional /.well-known/jwks.json. O conteudo vem de mocks/jwks.json.
 */
@Path("/.well-known/jwks.json")
public class JwksResource {

    @Inject
    MockStore store;

    @ConfigProperty(name = "mock.jwks.cache-max-age-seconds", defaultValue = "300")
    long cacheMaxAgeSeconds;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response jwks() {
        return Response.ok(store.json("jwks"))
                .type(MediaType.APPLICATION_JSON)
                .header("Cache-Control", "public, max-age=" + cacheMaxAgeSeconds)
                .build();
    }
}
