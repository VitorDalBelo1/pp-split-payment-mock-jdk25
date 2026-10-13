package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockResponses;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** Informe de Baixa (exceto por pagamento). */
@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BaixaExcetoPagamentoResource {

    private static final String MOCK = "pp-response-ok";

    @Inject
    MockResponses mocks;

    @POST
    @Path("/boleto/baixa-exceto-pagamento")
    public Response boleto(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }

    @POST
    @Path("/pix-automatico/baixa-exceto-pagamento")
    public Response pixAutomatico(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }

    @POST
    @Path("/pix-dinamico/baixa-exceto-pagamento")
    public Response pixDinamico(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created(MOCK);
    }
}
