package br.gov.pp.splitpayment.resource;

import br.gov.pp.splitpayment.mock.MockResponses;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Consulta Retroativa Super Inteligente.
 * Mesma mecanica do retorno super inteligente, porem o /start recebe
 * nsuInicial / nsuFinal / streamId (ignorados pelo mock).
 */
@Path("/api/v1/retroativo")
@Produces(MediaType.APPLICATION_JSON)
public class ConsultaRetroativaResource {

    @Inject
    MockResponses mocks;

    // ------------------------------------------------------------------ boleto

    @GET
    @Path("/boleto/{cnpjRaizPspRecDir}/transacoes/stream/start")
    public Response boletoStart(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                @QueryParam("nsuInicial") String nsuInicial,
                                @QueryParam("nsuFinal") String nsuFinal,
                                @QueryParam("streamId") String streamId,
                                @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retroativo-super-inteligente-boleto");
    }

    @GET
    @Path("/boleto/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response boletoToken(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                @PathParam("token") String token,
                                @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retroativo-super-inteligente-boleto");
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
                                       @QueryParam("nsuInicial") String nsuInicial,
                                       @QueryParam("nsuFinal") String nsuFinal,
                                       @QueryParam("streamId") String streamId,
                                       @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retroativo-super-inteligente-pix-automatico");
    }

    @GET
    @Path("/pix-automatico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixAutomaticoToken(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                       @PathParam("token") String token,
                                       @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retroativo-super-inteligente-pix-automatico");
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
                                     @QueryParam("nsuInicial") String nsuInicial,
                                     @QueryParam("nsuFinal") String nsuFinal,
                                     @QueryParam("streamId") String streamId,
                                     @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retroativo-super-inteligente-pix-dinamico");
    }

    @GET
    @Path("/pix-dinamico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixDinamicoToken(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                     @PathParam("token") String token,
                                     @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.streamNext("retroativo-super-inteligente-pix-dinamico");
    }

    @DELETE
    @Path("/pix-dinamico/{cnpjRaizPspRecDir}/transacoes/stream/{token}")
    public Response pixDinamicoDelete(@PathParam("cnpjRaizPspRecDir") String cnpjRaizPspRecDir,
                                      @PathParam("token") String token,
                                      @HeaderParam("X-JWS-Signature") String jws) {
        return mocks.noContent();
    }
}
