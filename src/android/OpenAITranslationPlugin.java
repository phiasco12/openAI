package com.example.openaitranslation;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class OpenAITranslationPlugin extends CordovaPlugin {
    private static final String OPENAI_API_KEY = "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"; // Replace with your OpenAI API key

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("translate")) {
            String text = args.getString(0);
            String targetLanguage = args.getString(1);

            try {
                String translatedText = translateText(text, targetLanguage);
                callbackContext.success(translatedText);
            } catch (IOException e) {
                callbackContext.error("Translation failed: " + e.getMessage());
            }
            return true;
        }
        return false;
    }

    private String translateText(String text, String targetLanguage) throws IOException {
        String openaiEndpoint = "https://api.openai.com/v1/engines/davinci-codex/completions";
        String openaiApiKey = OPENAI_API_KEY;
        String prompt = text + "\n\nTranslate the above text to " + targetLanguage + ":";

        URL url = new URL(openaiEndpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + openaiApiKey);
        connection.setDoOutput(true);

        String postData = "prompt=" + URLEncoder.encode(prompt, "UTF-8");
        connection.getOutputStream().write(postData.getBytes("UTF-8"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
