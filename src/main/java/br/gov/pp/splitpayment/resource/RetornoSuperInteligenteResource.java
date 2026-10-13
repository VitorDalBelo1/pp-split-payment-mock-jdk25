package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockResponses;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Retorno Super Inteligente (stream de saida).
 *
 * O mock sempre devolve 200 com um lote de transacoes e um novo "proximoToken";
 * o /start ainda devolve o header "streamId". O DELETE devolve 204.
 */
@Path("/api/v1/out")
@Produces(MediaType.APPLICATION_JSON)
public class RetornoSuperInteligenteResource {

    @Inject
    MockResponses mocks;

    // ------------------------------------------------------------------ boleto

    @GET
    @Path("/boleto/{cnpjRaizPspRecDir}/transacoes/stream/start")
    public Response boletoStart(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamStart("retorno-super-inteligente-boleto");
    }

    @GET
    @Path("/boleto/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response boletoToken(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                @PathParam("token") String token,
                                @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retorno-super-inteligente-boleto");
    }

    @DELETE
    @Path("/boleto/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response boletoDelete(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                 @PathParam("token") String token,
                                 @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.noContent();
    }

    // ----------------------------------------------------------- pix automatico

    @GET
    @Path("/pix-automatico/{cnpjRaizPspRecDir}/transacoes/stream/start")
    public Response pixAutomaticoStart(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                       @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamStart("retorno-super-inteligente-pix-automatico");
    }

    @GET
    @Path("/pix-automatico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixAutomaticoToken(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                       @PathParam("token") String token,
                                       @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retorno-super-inteligente-pix-automatico");
    }

    @DELETE
    @Path("/pix-automatico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixAutomaticoDelete(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                        @PathParam("token") String token,
                                        @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.noContent();
    }

    // ------------------------------------------------------------- pix dinamico

    @GET
    @Path("/pix-dinamico/{cnpjRaizPspRecDir}/transacoes/stream/start")
    public Response pixDinamicoStart(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                     @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamStart("retorno-super-inteligente-pix-dinamico");
    }

    @GET
    @Path("/pix-dinamico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixDinamicoToken(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                     @PathParam("token") String token,
                                     @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retorno-super-inteligente-pix-dinamico");
    }

    @DELETE
    @Path("/pix-dinamico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixDinamicoDelete(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                      @PathParam("token") String token,
                                      @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.noContent();
    }
}
