package com.project.snackcode.api;

import com.project.snackcode.model.openai.OpenaiRequest;
import com.project.snackcode.model.openai.OpenaiResponse;
import com.project.snackcode.model.openai.OpenaiResultModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Slf4j
public abstract class OpenaiProvider {

    @Value("${openai.api.key}")
    protected String apiKey;

    @Value("${openai.model}")
    protected String model;

    @Value("${openai.api.url}")
    protected String apiUrl;

    protected String question;

    protected RestTemplate restTemplate;

    @PostConstruct
    private void setHeaders() {

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            return execution.execute(request, body);
        });
    }

    public OpenaiResultModel getResult(String ...param) {
        OpenaiResultModel openaiResultModel = executeOperation(param);
        log.debug(question);
        return openaiResultModel;
    }

    protected abstract OpenaiResultModel executeOperation(String ...param);

    protected OpenaiResponse sendQuestion() {
        OpenaiRequest request           = OpenaiRequest.builder().model(model).message(question).build();
        OpenaiResponse response         = restTemplate.postForObject(apiUrl, request, OpenaiResponse.class);
        return response;
    }

    protected void setQuestion(String question) {
        this.question = question;
    }
}
