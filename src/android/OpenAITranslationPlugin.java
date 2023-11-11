package com.example.openaitranslation;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenAITranslationPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("translate".equals(action)) {
            String textToTranslate = args.getString(0);
            String translatedText = translateText(textToTranslate);
            callbackContext.success(translatedText);
            return true;
        }
        return false;
    }

    private String translateText(String text) {
        try {
            // Replace "YOUR_OPENAI_API_ENDPOINT" with the actual OpenAI API endpoint
            String baseUrl = "https://api.openai.com/v1/translate";
            String apiKey = "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";
            String apiUrl = baseUrl + "?text=" + text;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the authorization header with the API key
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                return content.toString();
            } else {
                // Handle error cases
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }
}
