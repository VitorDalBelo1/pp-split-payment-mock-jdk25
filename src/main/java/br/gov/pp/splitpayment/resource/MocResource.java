package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockResponses;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** Mecanismo de Ocorrencias (MOC): solicitacao, notificacao e consulta. */
@Path("/api/v1/moc")
@Produces(MediaType.APPLICATION_JSON)
public class MocResource {

    @Inject
    MockResponses mocks;

    @POST
    @Path("/solicitacao")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response solicitacao(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created("pp-response-ok");
    }

    @POST
    @Path("/notificacao")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response notificacao(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        return mocks.created("pp-response-ok");
    }

    @GET
    @Path("/{cnpjRaizPspRecDir}/ocorrencias")
    public Response consultarOcorrencias(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                         @QueryParam("nsuInicial") String nsuInicial,
                                         @QueryParam("limite") Integer limite,
                                         @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.ok("moc-ocorrencias");
    }
}
