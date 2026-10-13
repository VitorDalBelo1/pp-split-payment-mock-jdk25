package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockResponses;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Informe de Transacao Iniciada / Atualizada.
 * O corpo recebido e ignorado (nenhuma validacao ou regra de negocio).
 */
@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InformeTransacaoResource {

    private static final String MOCK = "pp-response-ok";

    @Inject
    MockResponses mocks;

    @POST
    @Path("/boleto")
    public Response boletoIniciada(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }

    @PATCH
    @Path("/boleto")
    public Response boletoAtualizada(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }

    @POST
    @Path("/pix-automatico")
    public Response pixAutomaticoIniciada(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }

    @POST
    @Path("/pix-dinamico")
    public Response pixDinamicoIniciada(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }

    @PATCH
    @Path("/pix-dinamico")
    public Response pixDinamicoAtualizada(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }
}
