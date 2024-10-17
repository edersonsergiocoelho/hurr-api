package br.com.escconsulting.service.inter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Duration;
import java.util.*;

public class IncluirPagamentoPix {

    public static void main (String[] args) throws URISyntaxException, IOException, InterruptedException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, InvalidKeySpecException {
        //String urlInter = "https://cdpj.partners.bancointer.com.br";
        String urlInter = "https://cdpj-sandbox.partners.uatinter.co";
        String nomeArquivoCertificado = "C:\\Users\\eders\\Downloads\\Inter_API-Chave_e_Certificado\\Sandbox_InterAPI_Certificado.crt";
        String nomeArquivoChave = "C:\\Users\\eders\\Downloads\\Inter_API-Chave_e_Certificado\\Sandbox_InterAPI_Chave.key";
        String clientId = "f9ee4a46-2b1a-4c9a-b66d-918a99c94af3";
        String clientSecret = "bdd0db05-0829-4044-b5ff-12326d84628e";
        String contaCorrente = "3559391-1";

        String permissoes = "pagamento-pix.write";

        KeyManagerFactory keyMgrFactory = getKeyManagerFactory(nomeArquivoCertificado, nomeArquivoChave);

        // populate SSLContext with key manager
        SSLContext sslCtx = SSLContext.getInstance("TLSv1.2");
        sslCtx.init(keyMgrFactory.getKeyManagers(), null, null);

        SSLParameters sslParam = new SSLParameters();
        sslParam.setNeedClientAuth(true);

        //Obter o bearer token
        ResponseTokenModel responseTokenModel = obtemBearerToken(urlInter, clientId, clientSecret, permissoes, sslCtx, sslParam);
        String bearerToken = responseTokenModel.getAccessToken();
        System.out.println("Bearer token = " + bearerToken);

        //Inclui um pagamento pix
        String idempotente = UUID.randomUUID().toString();
        String incluir = incluirPagamentoPix(urlInter, contaCorrente, idempotente, bearerToken, sslCtx, sslParam);
        System.out.println("Pagamento Pix = " + incluir);
    }

    private static String incluirPagamentoPix(String urlInter, String contaCorrente, String id, String accessToken, SSLContext sslCtx, SSLParameters sslParam) throws IOException, InterruptedException {

        var destinatario = new Destinatario();
        destinatario.setTipo("CHAVE");
        destinatario.setChave("<chave pix do destinatário>");

        var pagamentoPix = new PagamentoPixModel();
        pagamentoPix.setValor(BigDecimal.valueOf(100.00));
        pagamentoPix.setDescricao("Pagamento Pix");
        pagamentoPix.setDataPagamento("2024-04-27");
        pagamentoPix.setDestinatario(destinatario);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pagamentoPix);

        var body = HttpRequest.BodyPublishers.ofString(json);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(body)
                .uri(URI.create(urlInter + "/banking/v2/pix"))
                .setHeader("User-Agent", "Java 11 HttpClient") // add request header
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "Application/json")
                .header("x-conta-corrente", contaCorrente)
                .header("x-id-idempotente", id)
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .sslContext(sslCtx)
                .sslParameters(sslParam)
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    private static ResponseTokenModel obtemBearerToken(String urlInter, String clientId, String clientSecret, String permissoes, SSLContext sslCtx, SSLParameters sslParam) throws IOException, InterruptedException {
        // ----------------------------------------------
        //Acessa o Inter para obter o token
        // ----------------------------------------------
        Map<Object, Object> data = new HashMap<>();
        data.put("client_id", clientId);
        data.put("client_secret", clientSecret);
        data.put("scope", permissoes);
        data.put("grant_type", "client_credentials");

        HttpRequest.BodyPublisher body = ofFormData(data);

        HttpRequest requestToken = HttpRequest.newBuilder()
                .POST(body)
                .uri(URI.create(urlInter + "/oauth/v2/token"))
                .setHeader("User-Agent", "Java 11 HttpClient")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .sslContext(sslCtx)
                .sslParameters(sslParam)
                .build();

        HttpResponse<String> responseToken = httpClient.send(requestToken, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(responseToken.body(), ResponseTokenModel.class);
    }

    private static KeyManagerFactory getKeyManagerFactory(String nomeArquivoCertificado, String nomeArquivoChave) throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeySpecException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {
        // cert+key data. assuming X509 pem format
        byte[] cert = Files.readAllBytes(Path.of(nomeArquivoCertificado));

        byte[] keyTemp = Files.readAllBytes(Path.of(nomeArquivoChave));
        /*
        byte[] key = new String(keyTemp, Charset.defaultCharset())
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END PRIVATE KEY-----", "").getBytes();
         */
        String chavePrivadaString = new String(keyTemp, Charset.defaultCharset())
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", ""); // remove todos os espaços e quebras de linha
        byte[] key = chavePrivadaString.getBytes();

        // cert+key data. assuming X509 pem format

        // parse certificate
        final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        final Collection<? extends Certificate> chain = certificateFactory.generateCertificates(
                new ByteArrayInputStream(cert));

        byte[] encoded = Base64.getDecoder().decode(key);
        // parse key
        final Key keyEncoded = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encoded));

        // place cert+key into KeyStore
        KeyStore clientKeyStore = KeyStore.getInstance("jks");
        final char[] pwdChars = "fdrgsadrtersdarf".toCharArray();
        clientKeyStore.load(null, null);
        clientKeyStore.setKeyEntry("test", keyEncoded, pwdChars, chain.toArray(new Certificate[0]));

        // initialize KeyManagerFactory
        KeyManagerFactory keyMgrFactory = KeyManagerFactory.getInstance("SunX509");
        keyMgrFactory.init(clientKeyStore, pwdChars);

        return keyMgrFactory;
    }

    private static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }

        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public static class PagamentoPixModel {
        private BigDecimal valor;
        private String descricao;
        private String dataPagamento;
        private Destinatario destinatario;

        @JsonProperty("valor")
        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        @JsonProperty("descricao")
        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        @JsonProperty("dataPagamento")
        public String getDataPagamento() {
            return dataPagamento;
        }

        public void setDataPagamento(String dataPagamento) {
            this.dataPagamento = dataPagamento;
        }

        @JsonProperty("destinatario")
        public Destinatario getDestinatario() {
            return destinatario;
        }

        public void setDestinatario(Destinatario destinatario) {
            this.destinatario = destinatario;
        }
    }

    public static class Destinatario {
        public String tipo;
        public String chave;

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getChave() {
            return chave;
        }

        public void setChave(String chave) {
            this.chave = chave;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseTokenModel {
        private String accessToken;
        private String tokenType;
        private String expiresIn;
        private String scope;

        @JsonProperty("access_token")
        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        @JsonProperty("token_type")
        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        @JsonProperty("expires_in")
        public String getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(String expiresIn) {
            this.expiresIn = expiresIn;
        }

        @JsonProperty("scope")
        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }
}