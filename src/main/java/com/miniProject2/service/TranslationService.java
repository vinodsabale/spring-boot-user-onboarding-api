package com.miniProject2.service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationService {

    public String translateToMarathi(String text) {
        try {
            // Proper URL encoding
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());

            String apiUrl = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=mr&dt=t&q=" 
                            + encodedText;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);

            // Parse JSON
            JSONArray arr = new JSONArray(response);
            JSONArray translatedArr = arr.getJSONArray(0);
            JSONArray firstObj = translatedArr.getJSONArray(0);
            return firstObj.getString(0);

        } catch (Exception e) {
            return text; // fallback: original English text
        }
    }
}
