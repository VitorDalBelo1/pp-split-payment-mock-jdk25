# Plataforma Pública de Split Payment — API Mock (Quarkus / JDK 21)

Mock completo da API `Plataforma Pública de Split Payment` (OpenAPI v1.1.0).
Todos os **43 endpoints** do contrato estão implementados, mais o endpoint público de **JWKS** (`/.well-known/jwks.json`), e devolvem um payload de
sucesso estático. Não há validação de payload, regra de negócio, mTLS nem
verificação de assinatura JWS.

## Como rodar

```bash
./mvnw quarkus:dev          # ou: mvn quarkus:dev
```

> O projeto não inclui o wrapper `mvnw`. Se quiser gerá-lo: `mvn -N wrapper:wrapper`.

- Swagger UI: http://localhost:8080/swagger-ui
- OpenAPI (JSON): http://localhost:8080/openapi

Empacotar / rodar como jar:

```bash
mvn clean package
java -jar target/quarkus-app/quarkus-run.jar
```

## Como o Swagger é servido

O contrato original foi colocado em `src/main/resources/META-INF/openapi.json` e o
scan de anotações está desativado (`mp.openapi.scan.disable=true`). Assim o Swagger
UI exibe o documento **exatamente como recebido** — com todos os schemas, exemplos,
patterns e descrições — enquanto o código dos resources fica enxuto.

Se preferir que a documentação seja gerada a partir das anotações Java, basta
remover essa propriedade do `application.properties` e anotar os métodos com
`@Operation`, `@APIResponse` etc.

## Estrutura

```
src/main/java/br/gov/pp/splitpayment/
├── mock/
│   ├── MockStore.java        # lê e cacheia os JSONs de mock do classpath
│   └── MockResponses.java    # monta as respostas (status + headers do contrato)
└── resource/
    ├── InformeTransacaoResource.java          # POST/PATCH boleto, pix-automatico, pix-dinamico
    ├── InformePreliminarPagamentoResource.java # 6 arranjos
    ├── BaixaExcetoPagamentoResource.java       # 3 arranjos
    ├── SegregacaoResource.java                 # abertura, finalização e lotes
    ├── RetornoSuperInteligenteResource.java    # streams /api/v1/out
    ├── ConsultaRetroativaResource.java         # streams /api/v1/retroativo
    ├── MocResource.java                        # solicitação, notificação e consulta
    └── JwksResource.java                       # JWKS da PP (/.well-known/jwks.json)

src/main/resources/
├── META-INF/openapi.json    # contrato original (servido no Swagger)
└── mocks/*.json             # payloads de resposta
```

## Respostas devolvidas

| Grupo | Retorno |
|---|---|
| Informe de transação iniciada/atualizada | `201` + `pp-response-ok.json` |
| Informe preliminar de pagamento | `201` + `pp-response-ok.json` |
| Baixa exceto por pagamento | `201` + `pp-response-ok.json` |
| MOC solicitação/notificação | `201` + `pp-response-ok.json` |
| Segregação (abertura/finalização) | `201` + `pp-response-created-segregacao.json` |
| Segregação — lotes (6 arranjos) | `201` + `pp-response-created-lote.json` |
| Stream `/start` (out) | `200` + lote de transações, headers `streamId` e `proximoToken` |
| Stream `/{token}` (out e retroativo) | `200` + lote de transações, header `proximoToken` |
| Stream `DELETE` | `204` |
| Consulta MOC | `200` + `moc-ocorrencias.json` |

Todas as respostas trazem o header `X-JWS-Signature` com um valor fictício
(o mesmo exemplo publicado no contrato).

Os payloads de mock foram gerados a partir dos `examples` do próprio OpenAPI, então
já respeitam formatos, patterns e enums documentados.

## JWKS da Plataforma Pública

`GET /.well-known/jwks.json` publica as chaves públicas da PP (RFC 7517), conforme a
seção 5.4 do Manual de Segurança:

| `use` | kid | Finalidade |
|---|---|---|
| `sig` | `2026-06-key-1` | validação do `X-JWS-Signature` (RS256) |
| `tls` | `2026-06-mtls-1` | material de autenticação mTLS |

Cada chave traz `kid`, `alg`, `use`, validade (`notBefore`/`notAfter`, além do `x5c`) e
fingerprint (`x5t#S256`). O conteúdo vem de `src/main/resources/mocks/jwks.json` e a
resposta inclui `Cache-Control: public, max-age=300` (propriedade
`mock.jwks.cache-max-age-seconds`).

Observações:

- O endereço oficial está "a definir" no manual; `/.well-known/jwks.json` é o caminho convencional adotado.
- As chaves são **de teste** (certificados autoassinados) e as chaves privadas não são guardadas.
  Por isso o `X-JWS-Signature` fictício devolvido nas respostas **não é verificável** contra este JWKS.
- O endpoint é público (`security: []` no OpenAPI), sem mTLS.

## Customizando os mocks

Basta editar os arquivos em `src/main/resources/mocks/`. Em modo `quarkus:dev` o
hot reload aplica a mudança na hora — o `MockStore` mantém cache em memória, então
altere também o JSON e reinicie se o cache já tiver sido populado.

Para simular latência (útil para testar timeouts e long polling do cliente):

```properties
mock.delay-ms=1500
```

## O que o mock deliberadamente NÃO faz

- não valida nada do corpo da requisição (o body é recebido como `JsonNode` e ignorado);
- não exige mTLS nem valida o header `X-JWS-Signature` (ele é aceito e descartado);
- não mantém estado: `proximoToken` e `streamId` são sempre novos UUIDs e nenhum
  token é invalidado pelo `DELETE`;
- não simula os caminhos de erro (`400`, `403`, `410`, `422`, `500`) nem o `204`
  de "sem mensagens" — sempre devolve o caminho feliz;
- lotes de segregação sempre respondem `201`, nunca o `200` de "lote recebido
  anteriormente", pois isso exigiria controle de `idLote`.
