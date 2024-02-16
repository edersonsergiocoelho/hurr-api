package br.com.escconsulting.service.impl;

import br.com.escconsulting.service.WhatsAppService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WhatsAppServiceImpl implements WhatsAppService {

    @Value("${whatsapp.access.token}")
    private String accessToken;

    @Value("${whatsapp.from.phone.number.id}")
    private String fromPhoneNumberId;

    public void sendMessage(String toPhoneNumber, String messageContent) throws URISyntaxException, IOException, InterruptedException {

        JsonObject messageData = new JsonObject();
        messageData.addProperty("messaging_product", "whatsapp");
        messageData.addProperty("recipient_type", "individual");
        messageData.addProperty("to", toPhoneNumber);
        messageData.addProperty("type", "text");

        JsonObject textObject = new JsonObject();
        textObject.addProperty("preview_url", false);
        textObject.addProperty("body", messageContent);
        messageData.add("text", textObject);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://graph.facebook.com/v18.0/" + fromPhoneNumberId + "/messages"))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(messageData.toString()))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("Error sending WhatsApp message: " + response.body());
        } else {
            System.out.println("Message sent successfully: " + response.body());
        }
    }

    public void sendTemplateMessage(String toPhoneNumber, String code) throws URISyntaxException, IOException, InterruptedException {

        JsonObject messageData = new JsonObject();
        messageData.addProperty("messaging_product", "whatsapp");
        messageData.addProperty("to", toPhoneNumber);
        messageData.addProperty("type", "template");

        JsonObject templateObject = new JsonObject();
        templateObject.addProperty("name", "phone_verification_code_whatsapp ");

        JsonObject languageObject = new JsonObject();
        languageObject.addProperty("code", "pt_BR");

        templateObject.add("language", languageObject);

        // Adiciona componentes ao template
        JsonArray componentsArray = new JsonArray();

        // Componente do tipo "body"
        JsonObject bodyComponent = new JsonObject();
        bodyComponent.addProperty("type", "body");

        // Parâmetros do componente "body"
        JsonArray parametersArray = new JsonArray();

        JsonObject textParameter = new JsonObject();
        textParameter.addProperty("type", "text");
        textParameter.addProperty("text", code);

        parametersArray.add(textParameter);
        bodyComponent.add("parameters", parametersArray);

        componentsArray.add(bodyComponent);

        templateObject.add("components", componentsArray);

        messageData.add("template", templateObject);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://graph.facebook.com/v18.0/" + fromPhoneNumberId + "/messages"))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(messageData.toString()))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("Error sending WhatsApp template message: " + response.body());
        } else {
            System.out.println("Template message sent successfully: " + response.body());
        }
    }
}