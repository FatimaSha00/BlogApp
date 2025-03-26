package com.dheeraj.blogapp.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@Service
//public class OpenAiService {
//
//    @Value("${spring.ai.ollama.base-url}")
//    private String OPENAI_URL;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public Map<String, String> getSummary(String blogContent) {
//        Map<String, Object> request = Map.of(
//                "model", "mistral",
//                "messages", List.of(Map.of("role", "user", "content", "Summarize this blog:\n" + blogContent)),
//                "stream", false
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<Map> response;
//        try {
//            response = restTemplate.exchange(
//                    OPENAI_URL,
//                    HttpMethod.POST,
//                    requestEntity,
//                    Map.class
//            );
//        } catch (Exception e) {
//            return Map.of("summary", "Error: API request failed - " + e.getMessage());
//        }
//
//        // Log API response for debugging
//        System.out.println("Full API Response: " + response.getBody());
//
//        if (response.getBody() == null) {
//            return Map.of("summary", "Error: Empty API response");
//        }
//
//        // Extract response text
//        Object responseText = response.getBody().get("response");
//        if (responseText instanceof String summary) {
//            if (summary.isEmpty()) {
//                return Map.of("summary", "Error: AI did not generate a summary (possible system load issue)");
//            }
//            return Map.of("summary", summary.trim());
//        }
//
//        return Map.of("summary", "Error: Unexpected API response format");
//    }
//}




//@Service
//public class OpenAiService {
//
//    @Value("${spring.ai.ollama.base-url}")
//    private String OPENAI_URL;
//
//
//    public Map<String, String> getSummary(String blogContent) {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> request = Map.of(
//                "model", "mistral",
//                "messages", List.of(Map.of("role", "user", "content", blogContent)),
//                "stream", false
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<Map> response = restTemplate.exchange(
//                OPENAI_URL,
//                HttpMethod.POST,
//                requestEntity,
//                Map.class
//        );
//
//        // Log API response for debugging
//        System.out.println("API Response: " + response.getBody());
//
//        if (response.getBody() == null) {
//            return Map.of("summary", "Error: Empty API response");
//        }
//
//        // Extract "summary" key
//        Object summary = response.getBody().get("summary");
//
//        if (summary instanceof String) {
//            return Map.of("summary", ((String) summary).isEmpty() ? "Error: No summary generated" : (String) summary);
//        }
//
//        return Map.of("summary", "Error: Unexpected API response format");
//    }
//
//}
//

//
//@Service
//public class OpenAiService {
//
//    @Value("${spring.ai.ollama.base-url}")
//    private String OPENAI_URL;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public Map<String, String> getSummary(String blogContent) {
//        Map<String, Object> request = Map.of(
//                "model", "mistral",
//                "prompt", "Summarize this blog:\n" + blogContent,
//                "stream", false
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);
//
//        ResponseEntity<Map> response;
//        try {
//            response = restTemplate.exchange(
//                    OPENAI_URL,
//                    HttpMethod.POST,
//                    requestEntity,
//                    Map.class
//            );
//        } catch (Exception e) {
//            return Map.of("summary", "Error: API request failed - " + e.getMessage());
//        }
//
//        // Log API response for debugging
//        System.out.println("Full API Response: " + response.getBody());
//
//        if (response.getBody() == null) {
//            return Map.of("summary", "Error: Empty API response");
//        }
//
//        // Extract response text from Ollama's response format
//        Object responseText = response.getBody().get("response");
//        if (responseText instanceof String summary) {
//            String trimmedSummary = summary.trim();
//            if (trimmedSummary.isEmpty()) {
//                return Map.of("summary", "Error: AI did not generate a summary (possible system load issue)");
//            }
//            return Map.of("summary", trimmedSummary);
//        }
//
//        return Map.of("summary", "Error: Unexpected API response format - " + response.getBody());
//    }
//}


//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class OpenAiService {

//    private static final Logger logger = LoggerFactory.getLogger(OpenAiService.class);

//    @Value("${spring.ai.ollama.base-url}")
//    private String OLLAMA_URL;  // Should be http://localhost:11434/api/generate
//
//    private final RestTemplate restTemplate;
//
//    public Map<String, String> getSummary(String blogContent) {
//        // Ollama uses a different request format than OpenAI
//        Map<String, Object> request = new HashMap<>();
//        request.put("model", "mistral");
//        request.put("prompt", "Summarize this blog post in 3-5 sentences:\n" + blogContent);
//        request.put("stream", false);
//
//        // For better results, you can add parameters
//        request.put("temperature", 0.7);
//        request.put("max_tokens", 150);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);
//
//        try {
//            log.debug("Sending request to Ollama: {}", request);
//            ResponseEntity<Map> response = restTemplate.exchange(
//                    OLLAMA_URL,
//                    HttpMethod.POST,
//                    requestEntity,
//                    Map.class
//            );
//
//            log.debug("Received response from Ollama: {}", response.getBody());
//
//            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
//                return Map.of("summary", "Error: Invalid response from AI service");
//            }
//
//            // Ollama returns the response in a 'response' field
//            String summary = (String) response.getBody().get("response");
//            if (summary == null || summary.trim().isEmpty()) {
//                return Map.of("summary", "Error: Empty summary generated");
//            }
//
//            return Map.of("summary", summary.trim());
//
//        } catch (RestClientException e) {
//            log.error("Error calling Ollama API", e);
//            return Map.of("summary", "Error: Failed to connect to AI service - " + e.getMessage());
//        }
//    }
//}




//nda
@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAiService {

    @Value("${spring.ai.ollama.base-url}")
    private String OLLAMA_URL;

    private final RestTemplate restTemplate;

    public Map<String, String> getSummary(String blogContent) {
        // Step 1: Pre-process content to optimal length
        String processedContent = preprocessContent(blogContent);

        // Step 2: Create optimized prompt
        String prompt = createOptimizedPrompt(processedContent);

        // Step 3: Build request with performance-optimized parameters
        Map<String, Object> request = buildOptimizedRequest(prompt);

        // Step 4: Execute API call with timeouts
        return executeApiCall(request);
    }

    private String preprocessContent(String content) {
        // Take first 2000 characters or first 3 paragraphs
        String shortened = content.length() > 2000
                ? content.substring(0, 2000) + "\n[Content truncated...]"
                : content;

        // Split into paragraphs and take first 3
        String[] paragraphs = shortened.split("\\r?\\n\\r?\\n");
        return Arrays.stream(paragraphs)
                .limit(3)
                .collect(Collectors.joining("\n\n"));
    }

    private String createOptimizedPrompt(String content) {
        return """
            [INST] Generate a concise 3-sentence summary focusing on key points:
            - Omit examples and technical details
            - Use simple, clear language
            - Maximum 75 words
            
            Text:
            %s
            [/INST]""".formatted(content);
    }

    private Map<String, Object> buildOptimizedRequest(String prompt) {
        Map<String, Object> request = new HashMap<>();
        request.put("model", "mistral");
        request.put("prompt", prompt);
        request.put("stream", false);
        request.put("temperature", 0.3);  // More deterministic output
        request.put("max_tokens", 100);   // Strict length limit
        request.put("top_k", 20);         // Faster sampling
        request.put("num_predict", 100);  // Hard generation limit
        return request;
    }

    private Map<String, String> executeApiCall(Map<String, Object> request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);

        try {
            log.debug("Sending optimized request to Ollama");
            ResponseEntity<Map> response = restTemplate.exchange(
                    OLLAMA_URL,
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String summary = (String) response.getBody().get("response");
                if (summary != null && !summary.trim().isEmpty()) {
                    return Map.of("summary", postProcessSummary(summary));
                }
            }
            return Map.of("summary", "Error: Could not generate summary");

        } catch (RestClientException e) {
            log.error("Optimized API call failed", e);
            return Map.of("summary", "Error: " + e.getMessage());
        }
    }

    private String postProcessSummary(String summary) {
        // Remove any "Summary:" prefix and extra whitespace
        return summary.replaceAll("(?i)^summary[:\\s]*", "").trim();
    }
}