package br.com.escconsulting.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WhatsAppService {

    public void sendMessage(String toPhoneNumber, String messageContent) throws URISyntaxException, IOException, InterruptedException {
        String accessToken = "EAAFvUntsRBEBOzJ7mn0SFSUEuCQhDBjMwmFPOqpIovoxZAnw6VrLiNXrsaZB6OFeP1zYqDe5L9COnwem7ejkJoggzb7eqkKYIWZA2xhsSWfaHTris4SySTLsFl07Ql3ZCf4qB1DmXYpRl9YU0dZBAUxQxvZAk54MQWtZCkkZBe9YBMr3QRLjvSgtM2r63acHHejHRYu4pcwvHzMDiTVm";
        String fromPhoneNumberId = "223267017528976";

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

        // Handle response and potential errors
        if (response.statusCode() != 200) {
            // Throw an exception or log the error
            System.err.println("Error sending WhatsApp message: " + response.body());
        } else {
            System.out.println("Message sent successfully: " + response.body());
        }
    }

    public void sendTemplateMessage(String toPhoneNumber, String code) throws URISyntaxException, IOException, InterruptedException {
        String accessToken = "EAAFvUntsRBEBOzJ7mn0SFSUEuCQhDBjMwmFPOqpIovoxZAnw6VrLiNXrsaZB6OFeP1zYqDe5L9COnwem7ejkJoggzb7eqkKYIWZA2xhsSWfaHTris4SySTLsFl07Ql3ZCf4qB1DmXYpRl9YU0dZBAUxQxvZAk54MQWtZCkkZBe9YBMr3QRLjvSgtM2r63acHHejHRYu4pcwvHzMDiTVm";
        String fromPhoneNumberId = "223267017528976";

        JsonObject messageData = new JsonObject();
        messageData.addProperty("messaging_product", "whatsapp");
        messageData.addProperty("to", toPhoneNumber);
        messageData.addProperty("type", "template");

        JsonObject templateObject = new JsonObject();
        templateObject.addProperty("name", "send_code_whatsapp");

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

        // Handle response and potential errors
        if (response.statusCode() != 200) {
            // Throw an exception or log the error
            System.err.println("Error sending WhatsApp template message: " + response.body());
        } else {
            System.out.println("Template message sent successfully: " + response.body());
        }
    }
}