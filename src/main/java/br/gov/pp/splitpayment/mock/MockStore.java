package br.gov.pp.splitpayment.mock;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Carrega (e mantem em cache) os payloads de mock que ficam em
 * src/main/resources/mocks/*.json.
 *
 * Para alterar qualquer retorno da API basta editar o JSON correspondente -
 * nenhum codigo Java precisa ser tocado.
 */
@ApplicationScoped
public class MockStore {

    private static final String BASE_PATH = "mocks/";

    private final Map<String, String> cache = new ConcurrentHashMap<>();

    public String json(String name) {
        return cache.computeIfAbsent(name, this::read);
    }

    private String read(String name) {
        String resource = BASE_PATH + name + ".json";
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource)) {
            if (in == null) {
                throw new IllegalStateException("Mock nao encontrado no classpath: " + resource);
            }
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler o mock: " + resource, e);
        }
    }
}
