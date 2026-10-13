package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockResponses;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Informe de Segregacao: abertura, finalizacao e envio de lotes por arranjo.
 * Os lotes sempre respondem 201 (criado); o 200 "lote recebido anteriormente"
 * nao e simulado porque o mock nao mantem estado de idLote.
 */
@Path("/api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SegregacaoResource {

    private static final String MOCK_SEGREGACAO = "pp-response-created-segregacao";
    private static final String MOCK_LOTE = "pp-response-created-lote";

    @Inject
    MockResponses mocks;

    @POST
    @Path("/segregacao")
    public Response abrirSegregacao(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        System.out.println("/segregacao X-JWS-Signature: "+jws);
        Response resp = mocks.created(MOCK_SEGREGACAO);
        return Response.fromResponse(resp)
                .header("X-JWS-Signature",jws)
                .build();
    }

    @POST
    @Path("/segregacao/finalizacao")
    public Response finalizarSegregacao(@HeaderParam("X-JWS-Signature") String jws, JsonNode body) {
        System.out.println("/segregacao/finalizacao X-JWS-Signature: "+jws);
        Response resp = mocks.created(MOCK_SEGREGACAO);
        return Response.fromResponse(resp)
                .header("X-JWS-Signature",jws)
                .build();
    }

    @POST
    @Path("/boleto/segregacao/{idInfSegr}/lotes")
    public Response loteBoleto(@PathParam("idInfSegr") String idInfSegr,
                               @HeaderParam("X-JWS-Signature") String jws,
                               JsonNode body) {
        return mocks.created(MOCK_LOTE);
    }

    @POST
    @Path("/pix-automatico/segregacao/{idInfSegr}/lotes")
    public Response lotePixAutomatico(@PathParam("idInfSegr") String idInfSegr,
                                      @HeaderParam("X-JWS-Signature") String jws,
                                      JsonNode body) {
        return mocks.created(MOCK_LOTE);
    }

    @POST
    @Path("/pix-dinamico/segregacao/{idInfSegr}/lotes")
    public Response lotePixDinamico(@PathParam("idInfSegr") String idInfSegr,
                                    @HeaderParam("X-JWS-Signature") String jws,
                                    JsonNode body) {
        return mocks.created(MOCK_LOTE);
    }

    @POST
    @Path("/pix-estatico/segregacao/{idInfSegr}/lotes")
    public Response lotePixEstatico(@PathParam("idInfSegr") String idInfSegr,
                                    @HeaderParam("X-JWS-Signature") String jws,
                                    JsonNode body) {
        System.out.println("/pix-estatico/segregacao/{idInfSegr}/lotes X-JWS-Signature"+jws);
        Response resp = mocks.created(MOCK_LOTE);
        return Response.fromResponse(resp)
                .header("X-JWS-Signature",jws)
                .build();
    }

    @POST
    @Path("/ted/segregacao/{idInfSegr}/lotes")
    public Response loteTed(@PathParam("idInfSegr") String idInfSegr,
                            @HeaderParam("X-JWS-Signature") String jws,
                            JsonNode body) {
        return mocks.created(MOCK_LOTE);
    }

    @POST
    @Path("/tef/segregacao/{idInfSegr}/lotes")
    public Response loteTef(@PathParam("idInfSegr") String idInfSegr,
                            @HeaderParam("X-JWS-Signature") String jws,
                            JsonNode body) {
        return mocks.created(MOCK_LOTE);
    }
}
